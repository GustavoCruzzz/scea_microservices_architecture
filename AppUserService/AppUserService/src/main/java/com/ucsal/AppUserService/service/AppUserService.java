package com.ucsal.AppUserService.service;

import com.ucsal.AppUserService.token.ConfirmationToken;
import com.ucsal.AppUserService.dto.AppUserWithReservationDTO;
import com.ucsal.AppUserService.dto.ReservationDTO;
import com.ucsal.AppUserService.entity.AppUser;
import com.ucsal.AppUserService.entity.AppUserRole;
import com.ucsal.AppUserService.feign.ReservationInterface;
import com.ucsal.AppUserService.repository.AppUserRepository;
import com.ucsal.AppUserService.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
   private final ConfirmationTokenService confirmationTokenService;
    private final AppUserRepository repo;

    @Autowired
    private ReservationInterface reservationInterface;

    private static final String USER_NOT_FOUND_MSG = "Usuário com email %s não encontrado";

    public AppUserWithReservationDTO getUserWithReservations(Long userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + userId));

        List<ReservationDTO> reservations = reservationInterface.getReservationsByUser(userId);

        AppUserWithReservationDTO dto = new AppUserWithReservationDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setReservations(reservations);

        return dto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)
                        )
                );
    }

    public Optional<AppUser> findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return appUserRepository.findByEmail(email).isPresent();
    }

    public String signUpUser(AppUser appUser) {
        if (existsByEmail(appUser.getEmail())) {
            throw new IllegalStateException("Email já cadastrado");
        }
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        boolean isFirstUser = appUserRepository.count() == 0;
        appUser.setAppUserRole(isFirstUser ? AppUserRole.ADMIN : AppUserRole.USER);
        appUserRepository.save(appUser);
      return confirmationTokenService.createToken(appUser);
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    public AppUser loadUserByEmail(String email) {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)
                        )
                );
    }

    public void updatePassword(String email, String rawNewPassword) {
        AppUser user = loadUserByEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(rawNewPassword));
        appUserRepository.save(user);
    }

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public void deleteById(Long id) {
        appUserRepository.deleteById(id);
    }

    public AppUser updateRole(Long id, String newRole) {
        AppUser u = appUserRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + id));
        u.setAppUserRole(AppUserRole.valueOf(newRole));
        return appUserRepository.save(u);
    }

    @Transactional
    public void updateName(String email, String firstName, String lastName) {
        AppUser u = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
        u.setFirstName(firstName);
        u.setLastName(lastName);
        repo.save(u);
    }
}

package com.ucsal.AuthService.Config;

import com.ucsal.AuthService.Entity.AppUser;
import com.ucsal.AuthService.Entity.AppUserRole;
import com.ucsal.AuthService.Repository.AppUserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        // Verifica se já existe usuário com o email de teste
        if (appUserRepository.existsByEmail("teste@example.com")) {
            return;
        }

        AppUser user = new AppUser(
                "Teste",
                "User",
                "teste@example.com",
                passwordEncoder.encode("123456"),
                AppUserRole.USER
        );
        user.setEnabled(true);
        user.setLocked(false);

        appUserRepository.save(user);

        System.out.println("Usuário de teste criado: teste@example.com / 123456");
    }



}

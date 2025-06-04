package com.ucsal.AppUserService.Config;

import com.ucsal.AppUserService.entity.AppUser;
import com.ucsal.AppUserService.entity.AppUserRole;
import com.ucsal.AppUserService.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (appUserRepository.findByEmail("admin@ucsal.com").isEmpty()) {
            AppUser admin = new AppUser(
                    "Admin",
                    "UCSAL",
                    "admin@ucsal.com",
                    passwordEncoder.encode("admin123"),
                    AppUserRole.ADMIN
            );
            admin.setEnabled(true);
            appUserRepository.save(admin);
            System.out.println("Admin user created.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}

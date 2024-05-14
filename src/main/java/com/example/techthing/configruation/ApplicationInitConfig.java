package com.example.techthing.configruation;

import com.example.techthing.entity.Role;
import com.example.techthing.entity.User;
import com.example.techthing.repository.RoleRepository;
import com.example.techthing.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (roleRepository.findById("ADMIN").isEmpty()) {
                Role role = Role.builder()
                        .name("ADMIN")
                        .description("Role Admin")
                        .build();
                roleRepository.save(role);
            }

            if (roleRepository.findById("USER").isEmpty()) {
                Role role = Role.builder()
                        .name("USER")
                        .description("Role User")
                        .build();
                roleRepository.save(role);
            }

            if (userRepository.findByUsername("admin").isEmpty()) {
                HashSet<Role> roles = new HashSet<>();
                roles.add(roleRepository.findById("ADMIN").orElseThrow());
                User admin = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .addresses(null)
                        .build();

                userRepository.save(admin);
            }

        };
    }
}

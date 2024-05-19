package com.example.techthing.database;

import org.springframework.stereotype.Component;

import com.example.techthing.entity.Role;
import com.example.techthing.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleData {
    RoleRepository roleRepository;

    public void createBase() {
        if (this.roleRepository.count() == 0) {
            // admin role
            Role admin = Role.builder()
                    .name("ADMIN")
                    .description("Role Admin")
                    .build();
            this.roleRepository.save(admin);
            // manager role
            Role manager = Role.builder()
                    .name("MANAGER")
                    .description("Role Manager")
                    .build();
            this.roleRepository.save(manager);
            // user role
            Role user = Role.builder()
                    .name("USER")
                    .description("Role User")
                    .build();
            this.roleRepository.save(user);

            System.out.println("created base role");
        }
    }
}

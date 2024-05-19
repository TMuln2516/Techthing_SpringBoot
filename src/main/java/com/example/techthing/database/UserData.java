package com.example.techthing.database;

import java.util.HashSet;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.techthing.entity.Role;
import com.example.techthing.entity.User;
import com.example.techthing.repository.RoleRepository;
import com.example.techthing.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserData {
        UserRepository userRepository;
        PasswordEncoder passwordEncoder;
        RoleRepository roleRepository;

        public void createBase() {
                if (this.userRepository.count() == 0) {
                        // admin role
                        HashSet<Role> adminRole = new HashSet<>();
                        adminRole.add(this.roleRepository.findById("ADMIN").orElseThrow());
                        // manager role
                        HashSet<Role> managerRole = new HashSet<>();
                        managerRole.add(this.roleRepository.findById("MANAGER").orElseThrow());
                        // user role
                        HashSet<Role> userRole = new HashSet<>();
                        userRole.add(this.roleRepository.findById("USER").orElseThrow());

                        // admin user
                        User admin = User.builder()
                                        .username("admin")
                                        .password(this.passwordEncoder.encode("admin"))
                                        .fullname("I'm super admin")
                                        .roles(adminRole)
                                        .build();
                        userRepository.save(admin);
                        // manager user
                        User manager = User.builder()
                                        .username("manager")
                                        .password(this.passwordEncoder.encode("manager"))
                                        .fullname("I'm shop owner")
                                        .roles(managerRole)
                                        .build();
                        userRepository.save(manager);
                        // huy user
                        User huy = User.builder()
                                        .username("huy")
                                        .password(this.passwordEncoder.encode("huy@123456"))
                                        .fullname("Anh Huy")
                                        .roles(userRole)
                                        .build();
                        this.userRepository.save(huy);
                        // hong user
                        User hong = User.builder()
                                        .username("hong")
                                        .password(this.passwordEncoder.encode("hong@123456"))
                                        .fullname("Chi Hong")
                                        .roles(userRole)
                                        .build();
                        this.userRepository.save(hong);
                        // luc user
                        User luc = User.builder()
                                        .username("luc")
                                        .password(this.passwordEncoder.encode("luc@123456"))
                                        .fullname("Anh Luc")
                                        .roles(userRole)
                                        .build();
                        this.userRepository.save(luc);
                        // nhan user
                        User nhan = User.builder()
                                        .username("nhan")
                                        .password(this.passwordEncoder.encode("nhan@123456"))
                                        .fullname("Anh Nhan")
                                        .roles(userRole)
                                        .build();
                        this.userRepository.save(nhan);

                        System.out.println("CREATED BASE USER DATA");
                }
        }
}

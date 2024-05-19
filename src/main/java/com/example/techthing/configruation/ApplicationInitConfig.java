package com.example.techthing.configruation;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.techthing.database.RoleData;
import com.example.techthing.database.UserData;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    RoleData roleData;
    UserData userData;

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            this.roleData.createBase();
            this.userData.createBase();

        };
    }
}

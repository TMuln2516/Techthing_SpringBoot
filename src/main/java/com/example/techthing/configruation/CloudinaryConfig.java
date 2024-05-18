package com.example.techthing.configruation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {

    @Bean
    Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dygjmljlg",
                "api_key", "137576138927731",
                "api_secret", "VqkwBuKDDyBHYObYGPvoRy7-vsc"));
    }
}

package com.example.techthing.dto.request;

import com.example.techthing.validator.EmailConstrain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerifyOtpRequest {
    Integer otp;
    @EmailConstrain
    String mail;
}

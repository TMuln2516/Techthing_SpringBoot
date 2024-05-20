package com.example.techthing.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techthing.dto.request.ChangePasswordRequest;
import com.example.techthing.dto.request.MailRequest;
import com.example.techthing.dto.request.VerifyOtpRequest;
import com.example.techthing.dto.response.ApiResponse;
import com.example.techthing.dto.response.ChangePasswordResponse;
import com.example.techthing.service.ForgotPasswordService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/forgotPassword")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FogotPasswordController {
    ForgotPasswordService forgotPasswordService;

    @PostMapping("/verifyEmail")
    public ApiResponse<String> verifyEmail(@RequestBody @Valid MailRequest mailRequest) throws MessagingException {
        forgotPasswordService.sendOTP(mailRequest.getMail());
        return ApiResponse.<String>builder()
                .result("Send OTP success")
                .build();
    }

    @PostMapping("/verifyOtp")
    public ApiResponse<String> verifyOtp(@RequestBody @Valid VerifyOtpRequest verifyOtpRequest) {
        return ApiResponse.<String>builder()
                .result(forgotPasswordService.verifyOTP(verifyOtpRequest.getOtp(), verifyOtpRequest.getMail()))
                .build();
    }

    @PutMapping("/changePassword")
    public ApiResponse<ChangePasswordResponse> changePassword(
            @RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        return ApiResponse.<ChangePasswordResponse>builder()
                .result(forgotPasswordService.changePassword(changePasswordRequest))
                .build();
    }
}

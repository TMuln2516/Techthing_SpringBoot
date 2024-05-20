package com.example.techthing.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.techthing.dto.request.ChangePasswordRequest;
import com.example.techthing.dto.request.MailBody;
import com.example.techthing.dto.response.ChangePasswordResponse;
import com.example.techthing.entity.ForgotPassword;
import com.example.techthing.entity.User;
import com.example.techthing.exception.ErrorCode;
import com.example.techthing.exception.MyException;
import com.example.techthing.repository.ForgotPasswordRepository;
import com.example.techthing.repository.UserRepository;

import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ForgotPasswordService {
        ForgotPasswordRepository forgotPasswordRepository;
        UserRepository userRepository;
        EmailService emailService;
        PasswordEncoder passwordEncoder;

        public void sendOTP(String mail) throws MessagingException {
                User user = userRepository.findByMail(mail)
                                .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));

                // Check existed OTP
                Optional<ForgotPassword> checkExistedOTP = forgotPasswordRepository.findByUser(user);
                if (checkExistedOTP.isPresent()) {
                        forgotPasswordRepository.delete(checkExistedOTP.get());
                }

                // Generate OTP
                Random random = new Random();
                int otp = random.nextInt(100_000, 999_999);

                MailBody mailBody = MailBody.builder()
                                .to(mail)
                                .text("" + otp)
                                .subject("OTP verify")
                                .build();

                ForgotPassword forgotPassword = ForgotPassword.builder()
                                .otp(otp)
                                .expiryTime(new Date(
                                                Instant.now().plus(30, ChronoUnit.MINUTES).toEpochMilli()))
                                .user(user)
                                .build();

                emailService.sendSimpleMessage(mailBody);
                forgotPasswordRepository.save(forgotPassword);
        }

        public String verifyOTP(Integer otp, String mail) {
                User user = userRepository.findByMail(mail)
                                .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));
                ForgotPassword forgotPassword = forgotPasswordRepository.findByOtpAndUser(otp, user)
                                .orElseThrow(() -> new MyException(ErrorCode.INVALID_OTP));

                if (forgotPassword.getExpiryTime().before(Date.from(Instant.now()))) {
                        throw new MyException(ErrorCode.EXPIRED_OTP);
                }

                return "OTP verify";
        }

        public ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest) {
                User user = userRepository.findByMail(changePasswordRequest.getMail())
                                .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));
                ForgotPassword forgotPassword = forgotPasswordRepository
                                .findByOtpAndUser(changePasswordRequest.getOtp(), user)
                                .orElseThrow(() -> new MyException(ErrorCode.INVALID_OTP));

                if (!changePasswordRequest.getPassword().equals(changePasswordRequest.getPassword_confirm())) {
                        throw new MyException(ErrorCode.PASSWORD_NOT_MATCH);
                }

                User userchangePass = userRepository.findByMail(changePasswordRequest.getMail())
                                .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));
                userchangePass.setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
                userRepository.save(userchangePass);

                forgotPasswordRepository.deleteById(forgotPassword.getId());

                return ChangePasswordResponse.builder()
                                .username(user.getUsername())
                                .password(changePasswordRequest.getPassword())
                                .build();
        }

}

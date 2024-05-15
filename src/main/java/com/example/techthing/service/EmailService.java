package com.example.techthing.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.techthing.dto.request.MailBody;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    JavaMailSender javaMailSender;

    // Hàm Gửi Mail
    public void sendSimpleMessage(MailBody mailBody) {
        // Tạo Simple Mail
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailBody.to());
        message.setFrom("techthingute@gmail.com");
        message.setSubject(mailBody.subject());
        message.setText(mailBody.text());

        // Gửi Mail
        javaMailSender.send(message);
    }
}

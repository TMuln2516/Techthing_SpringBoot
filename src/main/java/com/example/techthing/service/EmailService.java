package com.example.techthing.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.techthing.dto.request.MailBody;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    JavaMailSender javaMailSender;

    // Hàm Gửi Mail
    public void sendSimpleMessage(MailBody mailBody) throws MessagingException {
        // Tạo Mime Message
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        // Cấu hình thông tin email
        helper.setTo(mailBody.to());
        helper.setFrom("techthingute@gmail.com");
        helper.setSubject(mailBody.subject());
        // Nội dung email HTML chứa OTP
        String htmlContent = "<html>"
                + "<body>"
                + "<p>Xin chào,</p>"
                + "<p>Đây là mã OTP của bạn:</p>"
                + "<div style='background-color: lightgray; padding: 10px; display: inline-block;'>"
                + "<h2 style='color: black; font-size: 24px; margin: 0; font-weight: bold; letter-spacing: 2px;'>"
                + mailBody.text() + "</h2>"
                + "</div>"
                + "<p>Vui lòng không chia sẻ mã này với bất kỳ ai.</p>"
                + "<p>Trân trọng,</p>"
                + "<p>Đội ngũ hỗ trợ TechThing</p>"
                + "</body>"
                + "</html>";

        helper.setText(htmlContent, true);

        // Gửi Mail
        javaMailSender.send(message);
    }
}

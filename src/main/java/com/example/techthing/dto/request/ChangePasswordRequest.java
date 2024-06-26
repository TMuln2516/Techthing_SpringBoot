package com.example.techthing.dto.request;

import com.example.techthing.validator.EmailConstrain;
import com.example.techthing.validator.PasswordConstrain;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangePasswordRequest {
    @EmailConstrain
    String mail;
    @PasswordConstrain
    String password;
    String password_confirm;
    Integer otp;
}

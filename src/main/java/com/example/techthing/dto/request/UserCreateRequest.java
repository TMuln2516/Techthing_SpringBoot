package com.example.techthing.dto.request;

import com.example.techthing.validator.EmailConstrain;
import com.example.techthing.validator.PasswordConstrain;
import com.example.techthing.validator.PhoneNumberConstrain;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    String username;
    @PasswordConstrain
    String password;
    String fullname;
    @EmailConstrain
    String mail;
    @PhoneNumberConstrain
    String phone;
}

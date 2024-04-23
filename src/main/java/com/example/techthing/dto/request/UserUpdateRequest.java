package com.example.techthing.dto.request;

import com.example.techthing.validator.EmailConstrain;
import com.example.techthing.validator.PasswordConstrain;
import com.example.techthing.validator.PhoneNumberConstrain;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @PasswordConstrain
    String password;
    String fullname;
    @EmailConstrain
    String mail;
    @PhoneNumberConstrain
    String phone;
}

package com.example.techthing.dto.request;

import com.example.techthing.validator.PasswordConstrain;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    String name;
    String username;
    @PasswordConstrain
    String password;
    String fullname;
    String mail;
    String phone;
}

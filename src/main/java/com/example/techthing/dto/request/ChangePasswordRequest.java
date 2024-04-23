package com.example.techthing.dto.request;

import com.example.techthing.validator.PasswordConstrain;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangePasswordRequest {
    @PasswordConstrain
    String password;
    String password_confirm;
}

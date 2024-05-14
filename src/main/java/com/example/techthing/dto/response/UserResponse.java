package com.example.techthing.dto.response;

import java.util.Set;

import com.example.techthing.entity.Address;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String fullname;
    String mail;
    String phone;
    Set<Address> addresses;
}

package com.example.techthing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techthing.dto.response.ApiResponse;
import com.example.techthing.dto.response.UserResponse;
import com.example.techthing.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {
    UserService userService;

    @GetMapping("/getAllUser")
    ApiResponse<List<UserResponse>> getAll() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAll())
                .build();
    }

    @DeleteMapping("/user/delete/{id}")
    ApiResponse<Void> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ApiResponse.<Void>builder()
                .build();
    }
}

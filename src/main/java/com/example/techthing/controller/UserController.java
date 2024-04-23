package com.example.techthing.controller;

import com.example.techthing.dto.request.ChangePasswordRequest;
import com.example.techthing.dto.request.UpdateBioRequest;
import com.example.techthing.dto.request.UserCreateRequest;
import com.example.techthing.dto.request.UserUpdateRequest;
import com.example.techthing.dto.response.ApiResponse;
import com.example.techthing.dto.response.ChangePasswordResponse;
import com.example.techthing.dto.response.UserResponse;
import com.example.techthing.entity.User;
import com.example.techthing.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.query.Param;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping("/getAll")
    ApiResponse<List<UserResponse>> getAll() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAll())
                .build();
    }

    @GetMapping("/bio/{id}")
    ApiResponse<User> getInfo(@PathVariable String id) {
        return ApiResponse.<User>builder()
                .result(userService.getUser(id))
                .build();
    }

    @PostMapping("/add")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(userCreateRequest))
                .build();
    }

    @PutMapping("/password/{id}")
    ApiResponse<ChangePasswordResponse> changePassword(@PathVariable String id, @RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        return ApiResponse.<ChangePasswordResponse>builder()
                .result(userService.changePassword(id, changePasswordRequest))
                .build();
    }

    @PutMapping("/update/bio/{id}")
    ApiResponse<UserResponse> updateBio(@PathVariable String id, @RequestBody @Valid UpdateBioRequest updateBioRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateBio(id, updateBioRequest))
                .build();
    }

    @DeleteMapping("delete/{id}")
    ApiResponse<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ApiResponse.<Void>builder()
                .build();
    }
}

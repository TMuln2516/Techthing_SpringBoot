package com.example.techthing.controller;

import com.example.techthing.dto.request.ChangePassRequest;
import com.example.techthing.dto.request.CheckPasswordRequest;
import com.example.techthing.dto.request.UpdateAddressRequest;
import com.example.techthing.dto.request.UpdateBioRequest;
import com.example.techthing.dto.request.UserCreateRequest;
import com.example.techthing.dto.response.ApiResponse;
import com.example.techthing.dto.response.ChangePasswordResponse;
import com.example.techthing.dto.response.UserResponse;
import com.example.techthing.entity.User;
import com.example.techthing.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.text.ParseException;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
        UserService userService;

        @GetMapping("/bio")
        ApiResponse<User> getInfo() {
                return ApiResponse.<User>builder()
                                .result(userService.getUser())
                                .build();
        }

        @PostMapping("/add")
        ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
                return ApiResponse.<UserResponse>builder()
                                .result(userService.createUser(userCreateRequest))
                                .build();
        }

        @PostMapping("/checkPassword")
        ApiResponse<Boolean> checkPassword(@RequestBody @Valid CheckPasswordRequest checkPasswordRequest)
                        throws ParseException {
                return ApiResponse.<Boolean>builder()
                                .result(userService.checkPassword(checkPasswordRequest))
                                .build();
        }

        @PutMapping("/changePassword")
        ApiResponse<ChangePasswordResponse> changePassword(@RequestBody @Valid ChangePassRequest changePassRequest)
                        throws ParseException {
                return ApiResponse.<ChangePasswordResponse>builder()
                                .result(userService.changePassword(changePassRequest))
                                .build();
        }

        @PutMapping("/update/bio")
        ApiResponse<UserResponse> updateBio(@RequestBody @Valid UpdateBioRequest updateBioRequest) {
                return ApiResponse.<UserResponse>builder()
                                .result(userService.updateBio(updateBioRequest))
                                .build();
        }

        @PutMapping("/update/address")
        ApiResponse<UserResponse> updateAddress(@RequestBody UpdateAddressRequest updateAddressRequest)
                        throws ParseException {
                return ApiResponse.<UserResponse>builder()
                                .result(userService.updateAddress(updateAddressRequest))
                                .build();
        }
}

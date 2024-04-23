package com.example.techthing.service;

import com.example.techthing.dto.request.ChangePasswordRequest;
import com.example.techthing.dto.request.UpdateBioRequest;
import com.example.techthing.dto.request.UserCreateRequest;
import com.example.techthing.dto.request.UserUpdateRequest;
import com.example.techthing.dto.response.ChangePasswordResponse;
import com.example.techthing.dto.response.UserResponse;
import com.example.techthing.entity.User;
import com.example.techthing.entity.Role;
import com.example.techthing.enums.Roles;
import com.example.techthing.exception.ErrorCode;
import com.example.techthing.exception.MyException;
import com.example.techthing.repository.RoleRepository;
import com.example.techthing.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreateRequest userCreateRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        if (userRepository.existsByUsername(userCreateRequest.getUsername())) {
            throw new MyException(ErrorCode.USER_EXISTED);
        }
        User newUser = new User();
        newUser.setUsername(userCreateRequest.getUsername());
        newUser.setFullname(userCreateRequest.getFullname());
        newUser.setMail(userCreateRequest.getMail());
        newUser.setPhone(userCreateRequest.getPhone());
        newUser.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(Roles.USER.name()).orElseThrow());
        newUser.setRoles(roles);
        userRepository.save(newUser);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(newUser.getId());
        userResponse.setUsername(newUser.getUsername());
        userResponse.setFullname(newUser.getFullname());
        userResponse.setMail(newUser.getMail());
        userResponse.setPhone(newUser.getPhone());

        return userResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : users) {
            UserResponse newUserResponse = UserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .fullname(user.getFullname())
                    .mail(user.getMail())
                    .phone(user.getPhone())
                    .build();
            userResponses.add(newUserResponse);
        }

        return userResponses;
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public ChangePasswordResponse changePassword(String id, ChangePasswordRequest changePasswordRequest) {
        if (!changePasswordRequest.getPassword().equals(changePasswordRequest.getPassword_confirm())) {
            throw new MyException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        User user = userRepository.findById(id).orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
        userRepository.save(user);

        return ChangePasswordResponse.builder()
                .username(user.getUsername())
                .password(changePasswordRequest.getPassword())
                .build();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse updateBio(String id, UpdateBioRequest updateBioRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));

        user.setFullname(updateBioRequest.getFullname());
        user.setMail(updateBioRequest.getMail());
        user.setPhone(updateBioRequest.getPhone());
        userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .username(user.getUsername())
                .mail(user.getMail())
                .phone(user.getPhone())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}

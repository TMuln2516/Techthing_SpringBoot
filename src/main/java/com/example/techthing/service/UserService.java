package com.example.techthing.service;

import com.example.techthing.dto.request.UserCreateRequest;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    public UserResponse createUser(UserCreateRequest userCreateRequest){
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
}

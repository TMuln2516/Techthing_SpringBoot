package com.example.techthing.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.techthing.dto.request.AddressRequest;
import com.example.techthing.dto.request.UpdateAddressRequest;
import com.example.techthing.dto.response.AddressResponse;
import com.example.techthing.entity.Address;
import com.example.techthing.entity.User;
import com.example.techthing.exception.ErrorCode;
import com.example.techthing.exception.MyException;
import com.example.techthing.repository.AddressRepository;
import com.example.techthing.repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressService {
    AddressRepository addressRepository;
    UserRepository userRepository;

    @NonFinal
    @Value("${myapp.signer-key}")
    protected String SIGNER_KEY;

    public AddressResponse createAddress(AddressRequest addressRequest) throws JOSEException, ParseException {
        Address newAddress = new Address();
        newAddress.setUsers(new HashSet<>());
        newAddress.setNumber(addressRequest.getNumber());

        // Verify Token
        SignedJWT signedJWT = SignedJWT.parse(addressRequest.getToken());

        // Find by username
        var username = signedJWT.getJWTClaimsSet().getSubject();
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));

        // Save Address
        newAddress.getUsers().add(user);
        addressRepository.save(newAddress);

        // Set Address for User
        user.getAddresses().add(newAddress);
        userRepository.save(user);

        // Map Address to AddressResponse
        return AddressResponse.builder()
                .id(newAddress.getId())
                .number(newAddress.getNumber())
                .build();
    }

    public AddressResponse updateAddress(String id, UpdateAddressRequest updateAddressRequest) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new MyException(ErrorCode.ADDRESS_NOT_EXISTED));
        address.setNumber(updateAddressRequest.getNumber());
        addressRepository.save(address);
        return AddressResponse.builder()
                .id(address.getId())
                .number(address.getNumber())
                .build();
    }

    public void deleteAddress(String id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new MyException(ErrorCode.ADDRESS_NOT_EXISTED));

        // Lấy danh sách người dùng liên kết với địa chỉ
        Set<User> users = address.getUsers();

        // Xóa địa chỉ khỏi danh sách địa chỉ của từng người dùng
        for (User user : users) {
            user.getAddresses().remove(address);
        }

        // Lưu các thay đổi vào bảng User
        userRepository.saveAll(users);

        // Tiến hành xóa địa chỉ từ bảng Address
        addressRepository.delete(address);
    }

}

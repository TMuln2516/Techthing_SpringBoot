package com.example.techthing.controller;

import java.text.ParseException;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techthing.dto.request.AddressRequest;
import com.example.techthing.dto.request.UpdateAddressRequest;
import com.example.techthing.dto.response.AddressResponse;
import com.example.techthing.dto.response.ApiResponse;
import com.example.techthing.service.AddressService;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressController {
    AddressService addressService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add")
    public ApiResponse<AddressResponse> createAddress(@RequestBody AddressRequest addressRequest)
            throws JOSEException, ParseException {
        return ApiResponse.<AddressResponse>builder()
                .result(addressService.createAddress(addressRequest))
                .build();
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{id}")
    public ApiResponse<AddressResponse> updateAddress(@PathVariable("id") String id,
            @RequestBody UpdateAddressRequest updateAddressRequest) {
        return ApiResponse.<AddressResponse>builder()
                .result(addressService.updateAddress(id, updateAddressRequest))
                .build();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteAddress(@PathVariable("id") String id) {
        addressService.deleteAddress(id);
        return ApiResponse.<Void>builder()
                .build();
    }
}

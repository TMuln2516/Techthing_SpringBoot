package com.example.techthing.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techthing.dto.request.AddToCartRequest;
import com.example.techthing.dto.response.ApiResponse;
import com.example.techthing.dto.response.CartDetailResponse;
import com.example.techthing.dto.response.GetCartResponse;
import com.example.techthing.service.CartDetailService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/cartdetails")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartDetailController {
    CartDetailService cartDetailService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/myCart")
    public ApiResponse<List<GetCartResponse>> getMyCart() {
        return ApiResponse.<List<GetCartResponse>>builder()
                .result(cartDetailService.getMyCart())
                .build();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/addToCart")
    public ApiResponse<CartDetailResponse> addToCart(@RequestBody AddToCartRequest addToCartRequest) {
        return ApiResponse.<CartDetailResponse>builder()
                .result(cartDetailService.addToCart(addToCartRequest))
                .build();
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/increase/{product_id}")
    public ApiResponse<GetCartResponse> increaseQuantity(@PathVariable("product_id") String product_id) {
        return ApiResponse.<GetCartResponse>builder()
                .result(cartDetailService.increaseQuantity(product_id))
                .build();
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/decrease/{product_id}")
    public ApiResponse<GetCartResponse> decreaseQuantity(@PathVariable("product_id") String product_id) {
        return ApiResponse.<GetCartResponse>builder()
                .result(cartDetailService.decreaseQuantity(product_id))
                .build();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteItem(@PathVariable("id") String id) {
        cartDetailService.delete(id);
        return ApiResponse.<Void>builder()
                .build();
    }

}

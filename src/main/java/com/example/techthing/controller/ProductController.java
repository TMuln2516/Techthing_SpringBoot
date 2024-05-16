package com.example.techthing.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techthing.dto.request.CreateProductRequest;
import com.example.techthing.dto.response.ApiResponse;
import com.example.techthing.dto.response.ProductResponse;
import com.example.techthing.entity.Product;
import com.example.techthing.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // create - admin
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin")
    ApiResponse<ProductResponse> create(@RequestBody CreateProductRequest createProductRequest) {
        return ApiResponse.<ProductResponse>builder()
                .result(this.productService.create(createProductRequest))
                .build();
    }

    // get all - public
    @GetMapping()
    ApiResponse<List<ProductResponse>> getAll() {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(this.productService.getAll())
                .build();
    }

}

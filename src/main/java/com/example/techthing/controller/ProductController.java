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

import com.example.techthing.dto.request.CreateProductRequest;
import com.example.techthing.dto.request.UpdateProductRequest;
import com.example.techthing.dto.response.ApiResponse;
import com.example.techthing.dto.response.ProductResponse;
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

    // get one - public
    @GetMapping("/{id}")
    ApiResponse<ProductResponse> getOne(@PathVariable("id") String id) {
        return ApiResponse.<ProductResponse>builder()
                .result(this.productService.getOne(id))
                .build();
    }

    // update - admin
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin")
    ApiResponse<ProductResponse> update(@RequestBody UpdateProductRequest updateProductRequest) {
        return ApiResponse.<ProductResponse>builder()
                .result(this.productService.update(updateProductRequest))
                .build();
    }

    // delete - admin
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}")
    ApiResponse<Void> deleteUser(@PathVariable("id") String id) {
        this.productService.delete(id);
        return ApiResponse.<Void>builder()
                .build();
    }
}

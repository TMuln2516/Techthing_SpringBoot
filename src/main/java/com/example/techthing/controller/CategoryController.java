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

import com.example.techthing.dto.request.CreateCategoryRequest;
import com.example.techthing.dto.request.UpdateCategoryRequest;
import com.example.techthing.dto.response.ApiResponse;
import com.example.techthing.dto.response.CategoryResponse;
import com.example.techthing.dto.response.DeleteResponse;
import com.example.techthing.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // create - admin
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin")
    ApiResponse<CategoryResponse> create(@RequestBody CreateCategoryRequest createCategoryRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .result(this.categoryService.create(createCategoryRequest))
                .build();
    }

    // get all - public
    @GetMapping()
    ApiResponse<List<CategoryResponse>> getAll() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(this.categoryService.getAll())
                .build();
    }

    // get one - public

    // update - admin
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin")
    ApiResponse<CategoryResponse> update(@RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .result(this.categoryService.update(updateCategoryRequest))
                .build();
    }

    // delete - admin
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}")
    ApiResponse<DeleteResponse> deleteUser(@PathVariable("id") String id) {
        return ApiResponse.<DeleteResponse>builder()
                .result(this.categoryService.delete(id))
                .build();
    }

}

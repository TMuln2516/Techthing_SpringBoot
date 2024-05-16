package com.example.techthing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.techthing.dto.request.CreateCategoryRequest;
import com.example.techthing.dto.request.UpdateCategoryRequest;
import com.example.techthing.dto.response.CategoryResponse;
import com.example.techthing.dto.response.DeleteResponse;
import com.example.techthing.entity.Category;
import com.example.techthing.exception.ErrorCode;
import com.example.techthing.exception.MyException;
import com.example.techthing.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepo;

    // DI
    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    // create
    public CategoryResponse create(CreateCategoryRequest createCategoryRequest) {

        if (this.categoryRepo.existsByName(createCategoryRequest.getName())) {
            throw new MyException(ErrorCode.CATEGORY_EXISTED);
        }

        Category newCategory = new Category();
        newCategory.setName(createCategoryRequest.getName());
        newCategory.setDescription(createCategoryRequest.getDescription());

        this.categoryRepo.save(newCategory);

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(newCategory.getId());
        categoryResponse.setName(newCategory.getName());
        categoryResponse.setDescription(newCategory.getDescription());

        return categoryResponse;
    }

    // get all
    public List<CategoryResponse> getAll() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        for (Category category : categories) {
            CategoryResponse temp = CategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .description(category.getDescription())
                    .build();
            categoryResponses.add(temp);
        }

        return categoryResponses;
    }

    // update
    public CategoryResponse update(UpdateCategoryRequest updateCategoryRequest) {

        Category category = this.categoryRepo.findById(updateCategoryRequest.getId())
                .orElseThrow(() -> new MyException(ErrorCode.CATEGORY_NOT_EXISTED));

        category.setId(updateCategoryRequest.getId());
        category.setName(updateCategoryRequest.getName());
        category.setDescription(updateCategoryRequest.getDescription());
        this.categoryRepo.save(category);

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    // delete
    public DeleteResponse delete(String id) {
        this.categoryRepo.findById(id)
                .orElseThrow(() -> new MyException(ErrorCode.CATEGORY_NOT_EXISTED));

        this.categoryRepo.deleteById(id);
        Optional<Category> category = this.categoryRepo.findById(id);
        int deleted = 1;
        if (category.isPresent()) {
            deleted = 0;
        }

        return DeleteResponse.builder()
                .deleted(deleted)
                .build();
    }
}
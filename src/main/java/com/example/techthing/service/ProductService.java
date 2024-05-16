package com.example.techthing.service;

import org.springframework.stereotype.Service;

import com.example.techthing.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    // Hello Huy ne

}

package com.example.techthing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techthing.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByName(String name);

    Optional<Product> findByName(String name);
}

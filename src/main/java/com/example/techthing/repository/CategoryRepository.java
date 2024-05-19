package com.example.techthing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techthing.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsByName(String name);

    Category findByName(String name);

}

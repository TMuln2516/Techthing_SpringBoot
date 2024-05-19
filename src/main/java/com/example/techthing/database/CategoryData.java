package com.example.techthing.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.techthing.entity.Category;
import com.example.techthing.repository.CategoryRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryData {
    CategoryRepository categoryRepository;

    public void createBase() {
        if (this.categoryRepository.count() == 0) {
            // phone
            Category phone = Category.builder()
                    .name("Phone")
                    .description("Phone category")
                    .build();
            // tablet
            Category tablet = Category.builder()
                    .name("Tablet")
                    .description("Tablet category")
                    .build();
            // laptop
            Category laptop = Category.builder()
                    .name("Laptop")
                    .description("Laptop category")
                    .build();
            // watch
            Category watch = Category.builder()
                    .name("Watch")
                    .description("Watch category")
                    .build();
            // sound
            Category sound = Category.builder()
                    .name("Sound")
                    .description("Sound category")
                    .build();
            // accessory
            Category accessory = Category.builder()
                    .name("Accessory")
                    .description("Accessory category")
                    .build();
            // pc
            Category pc = Category.builder()
                    .name("PC")
                    .description("PC category")
                    .build();
            // tv
            Category tv = Category.builder()
                    .name("TV")
                    .description("TV category")
                    .build();
            // save list
            List<Category> categories = new ArrayList<>(8);
            categories.add(phone);
            categories.add(tablet);
            categories.add(laptop);
            categories.add(watch);
            categories.add(sound);
            categories.add(accessory);
            categories.add(pc);
            categories.add(tv);
            this.categoryRepository.saveAll(categories);

            System.out.println("CREATED BASE CATEGORY DATA");
        }
    }
}

package com.example.techthing.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.techthing.dto.request.CreateProductRequest;
import com.example.techthing.dto.request.UpdateProductRequest;
import com.example.techthing.dto.response.CategoryResponse;
import com.example.techthing.dto.response.ProductResponse;
import com.example.techthing.entity.Category;
import com.example.techthing.entity.Product;
import com.example.techthing.exception.ErrorCode;
import com.example.techthing.exception.MyException;
import com.example.techthing.repository.CategoryRepository;
import com.example.techthing.repository.ProductRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
        ProductRepository productRepository;
        CategoryRepository categoryRepository;
        ImageService imageService;

        // create
        public ProductResponse create(CreateProductRequest createProductRequest, MultipartFile file)
                        throws IOException {

                Category category = categoryRepository.findById(createProductRequest.getCategoryId())
                                .orElseThrow(() -> new MyException(ErrorCode.CATEGORY_NOT_EXISTED));

                if (this.productRepository.existsByName(createProductRequest.getName())) {
                        throw new MyException(ErrorCode.PRODUCT_EXISTED);
                }

                String urlImage = imageService.uploadImage(file);

                Product product = Product.builder()
                                .name(createProductRequest.getName())
                                .price(createProductRequest.getPrice())
                                .image(urlImage)
                                .description(createProductRequest.getDescription())
                                .category(category)
                                .build();

                productRepository.save(product);

                return ProductResponse.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .price(product.getPrice())
                                .image(urlImage)
                                .description(product.getDescription())
                                .category(CategoryResponse.builder()
                                                .id(product.getCategory().getId())
                                                .name(product.getCategory().getName())
                                                .description(product.getCategory().getDescription())
                                                .build())
                                .build();
        }

        // get all
        public List<ProductResponse> getAll() {
                List<Product> products = productRepository.findAll();
                List<ProductResponse> productResponses = new ArrayList<>();

                for (Product product : products) {
                        ProductResponse temp = ProductResponse.builder()
                                        .id(product.getId())
                                        .name(product.getName())
                                        .price(product.getPrice())
                                        .image(product.getImage())
                                        .description(product.getDescription())
                                        .category(CategoryResponse.builder()
                                                        .id(product.getCategory().getId())
                                                        .name(product.getCategory().getName())
                                                        .description(product.getCategory().getDescription())
                                                        .build())
                                        .build();
                        productResponses.add(temp);
                }

                return productResponses;
        }

        // get one
        public ProductResponse getOne(String id) {
                Product product = this.productRepository.findById(id)
                                .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));

                return ProductResponse.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .price(product.getPrice())
                                .image(product.getImage())
                                .description(product.getDescription())
                                .category(CategoryResponse.builder()
                                                .id(product.getCategory().getId())
                                                .name(product.getCategory().getName())
                                                .description(product.getCategory().getDescription())
                                                .build())
                                .build();
        }

        // update
        public ProductResponse update(UpdateProductRequest updateProductRequest) {

                Product product = productRepository.findById(updateProductRequest.getId())
                                .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));

                if (productRepository.findByName(updateProductRequest.getName()).isPresent()) {
                        throw new MyException(ErrorCode.NAME_PRODUCT_EXISTED);
                }

                product.setName(updateProductRequest.getName());
                product.setPrice(updateProductRequest.getPrice());
                product.setImage(updateProductRequest.getImage());
                product.setDescription(updateProductRequest.getDescription());
                productRepository.save(product);

                return ProductResponse.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .price(product.getPrice())
                                .image(product.getImage())
                                .description(product.getDescription())
                                .category(CategoryResponse.builder()
                                                .id(product.getCategory().getId())
                                                .name(product.getCategory().getName())
                                                .description(product.getCategory().getDescription())
                                                .build())
                                .build();
        }

        // delete
        public void delete(String id) {
                if (!this.productRepository.existsById(id)) {
                        throw new MyException(ErrorCode.PRODUCT_NOT_EXISTED);
                }

                this.productRepository.deleteById(id);
        }

        public List<ProductResponse> searchProduct(String keyword) {
                // Tách từ khóa thành một mảng các từ
                String[] keywords = keyword.toLowerCase().split("\\s+");

                // Tìm kiếm sản phẩm
                List<Product> products = productRepository.findAll();

                // Lọc sản phẩm dựa trên các từ khóa
                List<ProductResponse> filteredProducts = products.stream()
                                .filter(product -> {
                                        String productName = product.getName().toLowerCase();
                                        return Arrays.stream(keywords)
                                                        .allMatch(productName::contains);
                                })
                                .map(product -> ProductResponse.builder()
                                                .id(product.getId())
                                                .name(product.getName())
                                                .price(product.getPrice())
                                                .image(product.getImage())
                                                .description(product.getDescription())
                                                .build())
                                .collect(Collectors.toList());

                return filteredProducts;
        }

}

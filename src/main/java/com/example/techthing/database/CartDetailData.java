package com.example.techthing.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.techthing.entity.Cart;
import com.example.techthing.entity.CartDetail;
import com.example.techthing.entity.Product;
import com.example.techthing.entity.User;
import com.example.techthing.exception.ErrorCode;
import com.example.techthing.exception.MyException;
import com.example.techthing.repository.CartDetailRepository;
import com.example.techthing.repository.ProductRepository;
import com.example.techthing.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartDetailData {
        ProductRepository productRepository;
        UserRepository userRepository;
        CartDetailRepository cartDetailRepository;

        public void createBase() {
                if (this.cartDetailRepository.count() == 0) {
                        // prepare prducts
                        Product phoneIPhone = this.productRepository.findByNameContainingIgnoreCase("iphone")
                                        .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));
                        Product phoneSamsung = this.productRepository.findByNameContainingIgnoreCase("samsung")
                                        .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));
                        Product tabletIPad = this.productRepository.findByNameContainingIgnoreCase("ipad")
                                        .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));
                        Product lapMacBook = this.productRepository.findByNameContainingIgnoreCase("macbook")
                                        .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));
                        Product lapLenovo = this.productRepository.findByNameContainingIgnoreCase("lenovo")
                                        .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));
                        Product watchApple = this.productRepository.findByNameContainingIgnoreCase("apple watch")
                                        .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));
                        Product accessoryLogitech = this.productRepository.findByNameContainingIgnoreCase("logitech")
                                        .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));
                        Product soundJBL = this.productRepository.findByNameContainingIgnoreCase("jbl")
                                        .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));
                        Product pcAsus = this.productRepository.findByNameContainingIgnoreCase("asus")
                                        .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));
                        Product tvSony = this.productRepository.findByNameContainingIgnoreCase("sony")
                                        .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));

                        // prepare carts
                        User huy = this.userRepository.findByUsername("huy")
                                        .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));
                        Cart huyCart = huy.getCart();
                        User hong = this.userRepository.findByUsername("hong")
                                        .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));
                        Cart hongCart = hong.getCart();
                        User luc = this.userRepository.findByUsername("luc")
                                        .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));
                        Cart lucCart = luc.getCart();
                        User nhan = this.userRepository.findByUsername("nhan")
                                        .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));
                        Cart nhanCart = nhan.getCart();

                        // prepare carts
                        CartDetail huyItem1 = CartDetail.builder()
                                        .cart(huyCart)
                                        .product(phoneSamsung)
                                        .quantity(9)
                                        .build();
                        CartDetail huyItem2 = CartDetail.builder()
                                        .cart(huyCart)
                                        .product(lapLenovo)
                                        .quantity(1)
                                        .build();
                        CartDetail hongItem1 = CartDetail.builder()
                                        .cart(hongCart)
                                        .product(phoneIPhone)
                                        .quantity(10)
                                        .build();
                        CartDetail hongItem2 = CartDetail.builder()
                                        .cart(hongCart)
                                        .product(lapMacBook)
                                        .quantity(4)
                                        .build();
                        CartDetail hongItem3 = CartDetail.builder()
                                        .cart(hongCart)
                                        .product(watchApple)
                                        .quantity(11)
                                        .build();
                        CartDetail hongItem4 = CartDetail.builder()
                                        .cart(hongCart)
                                        .product(tabletIPad)
                                        .quantity(6)
                                        .build();
                        CartDetail lucItem1 = CartDetail.builder()
                                        .cart(lucCart)
                                        .product(pcAsus)
                                        .quantity(5)
                                        .build();
                        CartDetail lucItem2 = CartDetail.builder()
                                        .cart(lucCart)
                                        .product(soundJBL)
                                        .quantity(8)
                                        .build();
                        CartDetail lucItem3 = CartDetail.builder()
                                        .cart(lucCart)
                                        .product(accessoryLogitech)
                                        .quantity(3)
                                        .build();
                        CartDetail nhanItem1 = CartDetail.builder()
                                        .cart(nhanCart)
                                        .product(phoneIPhone)
                                        .quantity(2)
                                        .build();
                        CartDetail nhanItem2 = CartDetail.builder()
                                        .cart(nhanCart)
                                        .product(phoneSamsung)
                                        .quantity(2)
                                        .build();
                        CartDetail nhanItem3 = CartDetail.builder()
                                        .cart(nhanCart)
                                        .product(tvSony)
                                        .quantity(7)
                                        .build();

                        // save cart details
                        List<CartDetail> productItems = new ArrayList<>(12);
                        productItems.add(huyItem1);
                        productItems.add(huyItem2);
                        productItems.add(hongItem1);
                        productItems.add(hongItem2);
                        productItems.add(hongItem3);
                        productItems.add(hongItem4);
                        productItems.add(lucItem1);
                        productItems.add(lucItem2);
                        productItems.add(lucItem3);
                        productItems.add(nhanItem1);
                        productItems.add(nhanItem2);
                        productItems.add(nhanItem3);
                        this.cartDetailRepository.saveAll(productItems);
                        System.out.println("CREATED BASE CART DETAIL DATA");
                }
        }
}

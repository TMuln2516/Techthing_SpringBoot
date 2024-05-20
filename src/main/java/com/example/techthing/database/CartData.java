package com.example.techthing.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.techthing.entity.Cart;
import com.example.techthing.entity.User;
import com.example.techthing.exception.ErrorCode;
import com.example.techthing.exception.MyException;
import com.example.techthing.repository.CartRepository;
import com.example.techthing.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartData {
    UserRepository userRepository;
    CartRepository cartRepository;

    public void createBase() {
        if (this.cartRepository.count() == 0) {
            // prepare users
            User huy = this.userRepository.findByUsername("huy")
                    .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));
            User hong = this.userRepository.findByUsername("hong")
                    .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));
            User luc = this.userRepository.findByUsername("luc")
                    .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));
            User nhan = this.userRepository.findByUsername("nhan")
                    .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));

            // prepare carts
            Cart huyCart = Cart.builder()
                    .user(huy)
                    .build();
            Cart lucCart = Cart.builder()
                    .user(luc)
                    .build();
            Cart hongCart = Cart.builder()
                    .user(hong)
                    .build();
            Cart nhanCart = Cart.builder()
                    .user(nhan)
                    .build();

            // save carts
            List<Cart> carts = new ArrayList<>(4);
            carts.add(huyCart);
            carts.add(hongCart);
            carts.add(lucCart);
            carts.add(nhanCart);
            this.cartRepository.saveAll(carts);

            System.out.println("CREATED BASE CART DATA");
        }
    }
}

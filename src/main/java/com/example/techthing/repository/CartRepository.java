package com.example.techthing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techthing.entity.Cart;
import com.example.techthing.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Optional<Cart> findByUser(User user);

}

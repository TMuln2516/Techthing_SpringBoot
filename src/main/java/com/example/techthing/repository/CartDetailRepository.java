package com.example.techthing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techthing.entity.Cart;
import com.example.techthing.entity.CartDetail;
import com.example.techthing.entity.Product;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, String> {
    boolean existsByProductAndCart(Product product, Cart cart);

    Optional<CartDetail> findByProductAndCart(Product product, Cart cart);

    List<CartDetail> findAllByCart(Cart cart);
}

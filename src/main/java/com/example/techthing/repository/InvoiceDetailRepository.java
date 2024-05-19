package com.example.techthing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techthing.entity.InvoiceDetail;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, String> {
    // boolean existsByProductAndCart(Product product, Cart cart);

    // Optional<CartDetail> findByProductAndCart(Product product, Cart cart);

    // List<CartDetail> findAllByCart(Cart cart);
}

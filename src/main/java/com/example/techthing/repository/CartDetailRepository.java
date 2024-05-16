package com.example.techthing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techthing.entity.CartDetail;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, String> {

}

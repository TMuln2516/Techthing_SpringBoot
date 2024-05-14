package com.example.techthing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techthing.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

}

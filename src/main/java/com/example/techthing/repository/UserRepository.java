package com.example.techthing.repository;

import com.example.techthing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);

    boolean existsByMail(String mail);

    Optional<User> findByUsername(String username);

    Optional<User> findByMail(String mail);

}

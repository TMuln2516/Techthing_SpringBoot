package com.example.techthing.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        String id;
        String username;
        String password;
        String fullname;
        String mail;
        String phone;
        String address;

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "user_role", joinColumns = {
                        @JoinColumn(name = "user_id") }, inverseJoinColumns = {
                                        @JoinColumn(name = "role_id") })
        @JsonManagedReference
        Set<Role> roles;

        @OneToMany(mappedBy = "user")
        @JsonManagedReference
        Set<Invoice> invoices;

        @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
        @JsonManagedReference
        Cart cart;

        @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
        ForgotPassword forgotPassword;
}

// shift alt o : remove import bị thừa

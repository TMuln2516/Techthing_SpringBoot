package com.example.techthing.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

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

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "user_role", joinColumns = {
                        @JoinColumn(name = "user_id") }, inverseJoinColumns = {
                                        @JoinColumn(name = "role_id") })
        Set<Role> roles;

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "user_address", joinColumns = {
                        @JoinColumn(name = "user_id") }, inverseJoinColumns = {
                                        @JoinColumn(name = "address_id") })
        Set<Address> addresses;

        @OneToMany(mappedBy = "user")
        Set<Invoice> invoices;

        @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = false)
        Cart cart;
}

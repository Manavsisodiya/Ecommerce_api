package com.manav.e_commerce_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manav.e_commerce_api.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
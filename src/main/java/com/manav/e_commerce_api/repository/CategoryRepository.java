package com.manav.e_commerce_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manav.e_commerce_api.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
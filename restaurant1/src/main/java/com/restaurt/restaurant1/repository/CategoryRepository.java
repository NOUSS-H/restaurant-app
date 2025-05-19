package com.restaurt.restaurant1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurt.restaurant1.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
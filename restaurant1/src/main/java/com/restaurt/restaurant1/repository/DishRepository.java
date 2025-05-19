package com.restaurt.restaurant1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurt.restaurant1.model.Category;
import com.restaurt.restaurant1.model.Dish;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByCategory(Category category);
}
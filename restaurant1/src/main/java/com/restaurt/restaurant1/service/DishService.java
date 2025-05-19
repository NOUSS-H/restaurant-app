package com.restaurt.restaurant1.service;

import org.springframework.stereotype.Service;

import com.restaurt.restaurant1.dto.DishDTO;
import com.restaurt.restaurant1.model.Category;
import com.restaurt.restaurant1.model.Dish;
import com.restaurt.restaurant1.repository.CategoryRepository;
import com.restaurt.restaurant1.repository.DishRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final CategoryRepository categoryRepository;

    public DishService(DishRepository dishRepository, CategoryRepository categoryRepository) {
        this.dishRepository = dishRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<DishDTO> getAllDishes() {
        return dishRepository.findAll().stream().map(dish -> new DishDTO(
                dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getImageUrl(),
                dish.getCategory() != null ? dish.getCategory().getId() : null,
                dish.getCategory() != null ? dish.getCategory().getName() : null
        )).collect(Collectors.toList());
    }

    public DishDTO addDish(DishDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Catégorie introuvable avec ID " + dto.getCategoryId()));

        Dish dish = new Dish(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getImageUrl(), category);
        dish = dishRepository.save(dish);

        return new DishDTO(
            dish.getId(),
            dish.getName(),
            dish.getDescription(),
            dish.getPrice(),
            dish.getImageUrl(),
            category.getId(),
            category.getName()
        );
    }

    public boolean deleteDish(Long id) {
        if (dishRepository.existsById(id)) {
            dishRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public DishDTO updateDish(Long id, DishDTO dto) {
        Dish dish = dishRepository.findById(id).orElse(null);
        if (dish == null) return null;

        dish.setName(dto.getName());
        dish.setDescription(dto.getDescription());
        dish.setPrice(dto.getPrice());
        dish.setImageUrl(dto.getImageUrl());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
            if (category != null) {
                dish.setCategory(category);
            }
        }

        Dish updated = dishRepository.save(dish);
        return new DishDTO(updated.getId(), updated.getName(), updated.getDescription(), updated.getPrice(),
                updated.getImageUrl(), updated.getCategory().getId(), updated.getCategory().getName());
    }
}
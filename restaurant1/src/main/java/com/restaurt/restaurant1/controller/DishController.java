package com.restaurt.restaurant1.controller;

import com.restaurt.restaurant1.dto.DishDTO;
import com.restaurt.restaurant1.service.DishService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@CrossOrigin(origins = "*")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public List<DishDTO> getAllDishes() {
        return dishService.getAllDishes();
    }

    @PostMapping
    public DishDTO createDish(@RequestBody DishDTO dishDTO) {
        return dishService.addDish(dishDTO);
    }

    @PutMapping("/{id}")
    public DishDTO updateDish(@PathVariable Long id, @RequestBody DishDTO dishDTO) {
        return dishService.updateDish(id, dishDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
    }
}


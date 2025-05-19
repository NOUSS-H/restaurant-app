package com.restaurt.restaurant1.model;

import jakarta.persistence.*;



import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Dish> dishes;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    // Getters et setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Dish> getDishes() { return dishes; }

    public void setDishes(List<Dish> dishes) { this.dishes = dishes; }
}

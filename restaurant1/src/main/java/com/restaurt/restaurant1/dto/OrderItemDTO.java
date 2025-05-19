package com.restaurt.restaurant1.dto;

public class OrderItemDTO {
    private Long dishId;
    private String dishName;
    private int quantity;

    public OrderItemDTO() {}

    public OrderItemDTO(Long dishId, String dishName, int quantity) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.quantity = quantity;
    }

	public Long getDishId() {
		return dishId;
	}

	public void setDishId(Long dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    // Getters et Setters
}
package com.restaurt.restaurant1.dto;

public class CartItemDTO {
    private Long dishId;
    private int quantity;

    public CartItemDTO() {}

    public CartItemDTO(Long dishId, int quantity) {
        this.dishId = dishId;
        this.quantity = quantity;
    }

	public Long getDishId() {
		return dishId;
	}

	public void setDishId(Long dishId) {
		this.dishId = dishId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    // Getters et Setters
}
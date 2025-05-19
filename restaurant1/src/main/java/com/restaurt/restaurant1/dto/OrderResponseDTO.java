package com.restaurt.restaurant1.dto;


import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {
    private Long id;
    private int numberOfPeople;
    private String note;
    private String status;
    private String clientUsername;
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClientUsername() {
		return clientUsername;
	}

	public void setClientUsername(String clientUsername) {
		this.clientUsername = clientUsername;
	}

	public String getCookUsername() {
		return cookUsername;
	}

	public void setCookUsername(String cookUsername) {
		this.cookUsername = cookUsername;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}

	private String cookUsername;
    private LocalDateTime createdAt;
    private List<OrderItemDTO> items;

    public OrderResponseDTO() {}

    public OrderResponseDTO(Long id, int numberOfPeople, String note, String status,
                            String clientUsername, String cookUsername, LocalDateTime createdAt,
                            List<OrderItemDTO> items) {
        this.id = id;
        this.numberOfPeople = numberOfPeople;
        this.note = note;
        this.status = status;
        this.clientUsername = clientUsername;
        this.cookUsername = cookUsername;
        this.createdAt = createdAt;
        this.items = items;
    }

    // Getters et Setters
}
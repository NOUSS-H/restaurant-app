package com.restaurt.restaurant1.dto;

import java.util.List;

public class OrderRequestDTO {

    private int numberOfPeople;
    private String note;
    private String clientUsername;
    private String cookUsername;
    private List<OrderItemDTO> items;

    public OrderRequestDTO() {
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }
    public String getCookUsername() {
        return cookUsername;
    }

    public void setCookUsername(String cookUsername) {
        this.cookUsername = cookUsername;
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

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}

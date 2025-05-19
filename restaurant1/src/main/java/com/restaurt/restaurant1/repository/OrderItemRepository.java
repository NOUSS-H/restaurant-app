package com.restaurt.restaurant1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurt.restaurant1.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

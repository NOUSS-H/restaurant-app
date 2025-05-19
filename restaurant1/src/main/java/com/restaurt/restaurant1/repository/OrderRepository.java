package com.restaurt.restaurant1.repository;

import com.restaurt.restaurant1.model.Order;
import com.restaurt.restaurant1.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.client.username = :username")
    List<Order> findByClientUsername(@Param("username") String username);

    @Query("SELECT o FROM Order o WHERE o.cook.username = :username")
    List<Order> findByCookUsername(@Param("username") String username); // 👈 ajoute cette ligne
    List<Order> findByStatus(OrderStatus status);
}

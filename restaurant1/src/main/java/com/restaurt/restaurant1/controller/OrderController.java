package com.restaurt.restaurant1.controller;

import com.restaurt.restaurant1.dto.OrderRequestDTO;
import com.restaurt.restaurant1.dto.OrderResponseDTO;
import com.restaurt.restaurant1.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*") 
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ✅ Créer une nouvelle commande
    @PostMapping
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO request) {
        return orderService.createOrder(request);
    }

    // ✅ Récupérer toutes les commandes
    @GetMapping
    public List<OrderResponseDTO> getAllOrders() {
        return orderService.getAllOrders();
    }
    
    
 // ✅ Récupérer les commandes d’un client spécifique par son username
    @GetMapping("/client/{username}")
    public List<OrderResponseDTO> getOrdersByClientUsername(@PathVariable String username) {
        return orderService.getOrdersByClientUsername(username);
    }


    // ✅ Modifier le statut d'une commande (ex: EN_COURS -> TERMINEE)
    @PutMapping("/{orderId}/status")
    public OrderResponseDTO updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status, // exemple : TERMINEE
            @RequestParam(required = false) String cookUsername) {
        return orderService.updateOrderStatus(orderId, status, cookUsername);
    }
}


package com.restaurt.restaurant1.service;

import org.springframework.stereotype.Service;

import com.restaurt.restaurant1.dto.OrderItemDTO;
import com.restaurt.restaurant1.dto.OrderRequestDTO;
import com.restaurt.restaurant1.dto.OrderResponseDTO;
import com.restaurt.restaurant1.model.*;
import com.restaurt.restaurant1.repository.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                        DishRepository dishRepository,
                        OrderItemRepository orderItemRepository,
                        UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        System.out.println("Client username: " + request.getClientUsername());

        Order order = new Order();
        order.setNumberOfPeople(request.getNumberOfPeople());
        order.setNote(request.getNote());
        order.setStatus(Order.Status.EN_COURS);
        order.setCreatedAt(LocalDateTime.now());

        // Associer le client
        User client = userRepository.findByUsername(request.getClientUsername())
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec le nom d'utilisateur : " + request.getClientUsername()));
        order.setClient(client);

        // ✅ Associer un cuisinier automatiquement si non fourni
        User cook;
        if (request.getCookUsername() != null && !request.getCookUsername().isEmpty()) {
            cook = userRepository.findByUsername(request.getCookUsername())
                    .orElseThrow(() -> new RuntimeException("Cuisinier non trouvé avec le nom d'utilisateur : " + request.getCookUsername()));
        } else {
            // 🔍 Trouver un cuisinier disponible (par rôle)
            List<User> cooks = userRepository.findByRole(User.Role.CUISINIER); 
            if (cooks.isEmpty()) {
                throw new RuntimeException("Aucun cuisinier disponible");
            }
            cook = cooks.get(0); // ou une autre logique d'affectation
        }
        order.setCook(cook);

        // Sauvegarder la commande pour l'associer à un ID
        orderRepository.save(order);

        // Créer les items de la commande
        List<OrderItem> items = request.getItems().stream()
                .map(itemDto -> {
                    Dish dish = dishRepository.findById(itemDto.getDishId())
                            .orElseThrow(() -> new RuntimeException("Plat non trouvé avec ID : " + itemDto.getDishId()));
                    OrderItem item = new OrderItem(order, dish, itemDto.getQuantity());
                    return orderItemRepository.save(item);
                })
                .collect(Collectors.toList());

        order.setItems(items);

        // Sauvegarder l'ordre à nouveau avec les items
        orderRepository.save(order);

        System.out.println("Client Username: " + request.getClientUsername());
        System.out.println("Cook Username: " + cook.getUsername());

        return toDTO(order);
    }



    public List<OrderResponseDTO> getOrdersByClientUsername(String username) {
    	 System.out.println("Recherche des commandes pour le client : " + username);
        List<Order> orders = orderRepository.findByClientUsername(username);
        System.out.println("Nombre de commandes trouvées : " + orders.size());
        return orders.stream()
                     .map(this::toDTO)
                     .collect(Collectors.toList());
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public OrderResponseDTO updateOrderStatus(Long orderId, String status, String cookUsername) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new RuntimeException("Commande introuvable avec ID " + orderId);
        }

        Order order = optionalOrder.get();

        try {
            order.setStatus(Order.Status.valueOf(status));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Statut invalide : " + status);
        }

        if (cookUsername != null && !cookUsername.isEmpty()) {
            User cook = userRepository.findByUsername(cookUsername)
                .orElseThrow(() -> new RuntimeException("Cuisinier non trouvé avec le nom d'utilisateur : " + cookUsername));
            order.setCook(cook);
        }

        orderRepository.save(order);

        return toDTO(order);
    }

    private OrderResponseDTO toDTO(Order order) {
        List<OrderItemDTO> items = order.getItems() != null ?
                order.getItems().stream()
                        .map(i -> new OrderItemDTO(
                                i.getDish().getId(),
                                i.getDish().getName(),
                                i.getQuantity()))
                        .collect(Collectors.toList())
                : new ArrayList<>();

        return new OrderResponseDTO(
                order.getId(),
                order.getNumberOfPeople(),
                order.getNote(),
                order.getStatus().name(),
                order.getClient() != null ? order.getClient().getUsername() : null,
                order.getCook() != null ? order.getCook().getUsername() : null,
                order.getCreatedAt(),
                items
        );
    }
}

package com.restaurt.restaurant1.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Order> clientOrders;

    @OneToMany(mappedBy = "cook", fetch = FetchType.LAZY)
    private List<Order> cookOrders;

    public enum Role {
        ADMIN, CLIENT, CUISINIER
    }

    public User() {}

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getClientOrders() {
        return clientOrders;
    }

    public void setClientOrders(List<Order> clientOrders) {
        this.clientOrders = clientOrders;
    }

    public List<Order> getCookOrders() {
        return cookOrders;
    }

    public void setCookOrders(List<Order> cookOrders) {
        this.cookOrders = cookOrders;
    }
}

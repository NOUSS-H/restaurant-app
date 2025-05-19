package com.restaurt.restaurant1.controller;

import com.restaurt.restaurant1.dto.UserDTO;
import com.restaurt.restaurant1.model.User;
import com.restaurt.restaurant1.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Enregistrement
    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // ✅ Connexion
    @PostMapping("/login")
    public UserDTO login(@RequestBody User loginRequest) {
        return userService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }
}

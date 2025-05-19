package com.restaurt.restaurant1.service;

import com.restaurt.restaurant1.dto.UserDTO;
import com.restaurt.restaurant1.model.User;
import com.restaurt.restaurant1.model.User.Role;
import com.restaurt.restaurant1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 🔐 Login
    public UserDTO login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return new UserDTO(user.getId(), user.getUsername(), user.getRole().name());
            }
        }
        return null;
    }

    // 🆕 Register
    public UserDTO registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Nom d'utilisateur déjà utilisé");
        }

        // ✅ Si le rôle est null, on affecte le rôle CLIENT par défaut
        if (user.getRole() == null) {
            user.setRole(Role.CLIENT);
        }

        User saved = userRepository.save(user);
        return new UserDTO(saved.getId(), saved.getUsername(), saved.getRole().name());
    }

    // 🔎 Get User by Username
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    // 🔎 Get User by ID
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}

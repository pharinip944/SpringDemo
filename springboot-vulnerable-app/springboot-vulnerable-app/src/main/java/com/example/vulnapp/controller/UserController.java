package com.example.vulnapp.controller;

import com.example.vulnapp.model.User;
import com.example.vulnapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        // Explicit input sanitization for username
        if (user.getUsername() == null || !user.getUsername().matches("^[a-zA-Z0-9_.-]{3,32}$")) {
            throw new IllegalArgumentException("Invalid username: only alphanumeric characters, underscores, hyphens, and periods are allowed (3-32 chars)");
        }
        // Additional sanitization/validation can be added here for other fields
        return userService.createUser(user);
    }

    // Other controller methods...
}

package com.example.vulnapp.controller;

import com.example.vulnapp.model.User;
import com.example.vulnapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid user input");
        }
        // Additional manual sanitization if needed
        // Example: user.setUsername(HtmlUtils.htmlEscape(user.getUsername()));
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
    // Other endpoints remain unchanged
}

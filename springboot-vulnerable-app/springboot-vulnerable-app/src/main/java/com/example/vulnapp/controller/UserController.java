
package com.example.vulnapp.controller;

import com.example.vulnapp.model.User;
import com.example.vulnapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //No authentication / authorization
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    //IDOR vulnerability
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    //No input validation
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.save(user);
    }
}

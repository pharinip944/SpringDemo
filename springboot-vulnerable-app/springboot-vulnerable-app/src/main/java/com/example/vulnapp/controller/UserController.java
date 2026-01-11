package com.example.vulnapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.example.vulnapp.model.User;
import com.example.vulnapp.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/csrf-token")
    public CsrfToken csrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user, HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken == null || !csrfToken.getToken().equals(request.getHeader("X-CSRF-TOKEN"))) {
            throw new SecurityException("Invalid CSRF token");
        }
        return userService.createUser(user);
    }

    // Other endpoints ...
}

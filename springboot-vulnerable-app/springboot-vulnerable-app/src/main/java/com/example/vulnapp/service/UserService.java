package com.example.vulnapp.service;

import com.example.vulnapp.model.User;
import com.example.vulnapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User getById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = repository.findByUsername(currentUsername);
        if (currentUser == null) {
            throw new SecurityException("User not authenticated");
        }
        // Only allow access to own data or if user has admin role
        if (!currentUser.getId().equals(id) && !currentUser.getRoles().contains("ROLE_ADMIN")) {
            throw new SecurityException("Unauthorized access to user data");
        }
        return repository.findById(id);
    }
    // other methods...
}

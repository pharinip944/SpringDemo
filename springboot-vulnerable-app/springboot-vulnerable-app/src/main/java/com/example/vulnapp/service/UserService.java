package com.example.vulnapp.service;

import com.example.vulnapp.model.User;
import com.example.vulnapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(Long id, User currentUser) {
        if (currentUser == null) {
            return Optional.empty();
        }
        // Authorization: Only allow access if current user is admin or matches the requested id
        if (!currentUser.getId().equals(id) && (currentUser.getRoles() == null || !currentUser.getRoles().contains("ROLE_ADMIN"))) {
            return Optional.empty();
        }
        return userRepository.findById(id);
    }

    // Other service methods...
}

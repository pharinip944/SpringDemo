package com.example.vulnapp.service;

import com.example.vulnapp.model.User;
import com.example.vulnapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("#id == authentication.principal.id or hasRole('ROLE_ADMIN')")
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @PreAuthorize("#id == authentication.principal.id or hasRole('ROLE_ADMIN')")
    public User updateUser(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

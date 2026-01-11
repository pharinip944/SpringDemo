
package com.example.vulnapp.service;

import com.example.vulnapp.model.User;
import com.example.vulnapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    // No authorization check
    public User getById(Long id) {
        return repository.findById(id);
    }

    public User save(User user) {
        return repository.save(user);
    }
}

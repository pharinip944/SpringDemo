package com.example.vulnapp.repository;

import com.example.vulnapp.model.User;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private Map<Long, User> users = new HashMap<>();

    private boolean isAuthorized(Long userId, Long requesterId) {
        return userId.equals(requesterId);
    }

    public User findById(Long userId, Long requesterId) {
        if (!isAuthorized(userId, requesterId)) {
            throw new SecurityException("Unauthorized access");
        }
        return users.get(userId);
    }

    public User save(Long requesterId, User user) {
        if (!isAuthorized(user.getId(), requesterId)) {
            throw new SecurityException("Unauthorized access");
        }
        users.put(user.getId(), user);
        return user;
    }

    public void delete(Long userId, Long requesterId) {
        if (!isAuthorized(userId, requesterId)) {
            throw new SecurityException("Unauthorized access");
        }
        users.remove(userId);
    }

    // Add similar authorization checks to other repository methods as needed
}

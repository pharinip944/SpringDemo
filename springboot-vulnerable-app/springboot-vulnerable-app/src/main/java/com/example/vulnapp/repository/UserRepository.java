package com.example.vulnapp.repository;

import com.example.vulnapp.model.User;
import java.util.Map;
import java.util.HashMap;

public class UserRepository {
    private Map<Long, User> store = new HashMap<>();

    // Example authorization check method (should be replaced with real logic)
    private boolean isAuthorized(Long userId, Long requesterId) {
        // Only allow access if the user is requesting their own data
        return userId.equals(requesterId);
    }

    // Secure findById with authorization check
    public User findById(Long id, Long requesterId) {
        if (!isAuthorized(id, requesterId)) {
            throw new SecurityException("Unauthorized access to user data");
        }
        return store.get(id);
    }

    // Existing methods...
}

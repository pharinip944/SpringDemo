
package com.example.vulnapp.repository;

import com.example.vulnapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private final Map<Long, User> store = new HashMap<>();

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    // Insecure direct object access
    public User findById(Long id) {
        return store.get(id);
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId((long) (store.size() + 1));
        }
        store.put(user.getId(), user);
        return user;
    }
}

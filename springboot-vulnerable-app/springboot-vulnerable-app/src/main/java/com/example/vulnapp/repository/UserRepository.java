package com.example.vulnapp.repository;

import com.example.vulnapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Removed authorization logic from repository
    // Authorization should be handled in the service/controller layer
    User findByUsername(String username);
    // Other data access methods as needed
}

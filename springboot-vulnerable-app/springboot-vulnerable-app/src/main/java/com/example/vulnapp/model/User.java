package com.example.vulnapp.model;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class User {
    private Long id;
    private String username;
    private String passwordHash;

    public User() {}

    public User(Long id, String username, String plainPassword) {
        this.id = id;
        this.username = username;
        setPassword(plainPassword);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(String plainPassword) {
        // Use BCrypt for strong hashing and automatic salting
        this.passwordHash = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public boolean checkPassword(String plainPassword) {
        return BCrypt.checkpw(plainPassword, this.passwordHash);
    }
}

package com.example.vulnapp.model;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class User {
    private Long id;
    private String username;
    private String passwordHash;

    public User() {}

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        setPassword(password);
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

    public void setPassword(String password) {
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.passwordHash);
    }
}

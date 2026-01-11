package com.example.vulnapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import com.example.vulnapp.security.CSRFTokenManager;
import com.example.vulnapp.model.User;
import com.example.vulnapp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/update")
    public String updateUser(HttpServletRequest request, @RequestBody User user) {
        String sessionToken = CSRFTokenManager.getTokenFromSession(request);
        String headerToken = request.getHeader("X-CSRF-TOKEN");

        if (sessionToken == null || headerToken == null || !CSRFTokenManager.isValidToken(sessionToken, headerToken)) {
            throw new SecurityException("Invalid or missing CSRF token");
        }

        // Invalidate the CSRF token after use to prevent reuse
        CSRFTokenManager.invalidateToken(request);

        userService.updateUser(user);
        return "User updated successfully.";
    }
}

// CSRFTokenManager.java
package com.example.vulnapp.security;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.Objects;

public class CSRFTokenManager {
    public static String getTokenFromSession(HttpServletRequest request) {
        Object token = request.getSession().getAttribute("CSRF_TOKEN");
        return token != null ? token.toString() : null;
    }

    public static boolean isValidToken(String sessionToken, String headerToken) {
        return constantTimeEquals(sessionToken, headerToken);
    }

    public static void invalidateToken(HttpServletRequest request) {
        request.getSession().removeAttribute("CSRF_TOKEN");
    }

    private static boolean constantTimeEquals(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) return false;
        int result = 0;
        for (int i = 0; i < a.length(); i++) {
            result |= a.charAt(i) ^ b.charAt(i);
        }
        return result == 0;
    }
}

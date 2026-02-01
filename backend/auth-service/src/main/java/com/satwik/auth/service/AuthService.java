package com.satwik.auth.service;

import org.springframework.stereotype.Service;
import com.satwik.auth.repository.UserRepository;
import com.satwik.auth.model.User;
import com.satwik.auth.dto.*;

import java.util.Map;

@Service
public class AuthService {

    private final UserRepository repo;
    private final EmailService email;

    public AuthService(UserRepository repo, EmailService email) {
        this.repo = repo;
        this.email = email;
    }

    public void register(RegisterRequest req) {
        User u = new User();
        u.setUsername(req.username);
        u.setEmail(req.email);
        u.setPasswordHash(req.password); // ⚠️ TEMP: plain password
        u.setVerificationCode("" + (int)(Math.random() * 900000 + 100000));
        u.setVerified(false);

        repo.save(u);
        email.send(u.getEmail(), u.getVerificationCode());
    }

    public void verify(VerifyRequest req) {
        User u = repo.findByEmail(req.email).orElseThrow();
        if (!u.getVerificationCode().equals(req.code)) {
            throw new RuntimeException("Invalid code");
        }
        u.setVerified(true);
        repo.save(u);
    }

    public Map<String, String> login(LoginRequest req) {
        User u = repo.findByUsername(req.username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!u.isVerified()) {
            throw new RuntimeException("Not verified");
        }

        if (!u.getPasswordHash().equals(req.password)) {
            throw new RuntimeException("Wrong password");
        }

        return Map.of(
            "message", "Hi " + u.getUsername() + ", Welcome to the beautiful world"
        );
    }
}


package com.satwik.auth.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.satwik.auth.service.AuthService;
import com.satwik.auth.dto.LoginRequest;
import com.satwik.auth.dto.RegisterRequest;
import com.satwik.auth.dto.VerifyRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        service.register(request);
    }

    @PostMapping("/verify")
    public void verify(@RequestBody VerifyRequest request) {
        service.verify(request);
    }

    // ✅ LOGIN — SERVICE ALREADY RETURNS Map<String, String>
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        System.out.println("LOGIN CONTROLLER HIT");
        return service.login(request);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}


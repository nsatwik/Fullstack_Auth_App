
package com.satwik.auth.controller;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import com.satwik.auth.service.AuthService;
import com.satwik.auth.dto.*;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
 private final AuthService service;
 public AuthController(AuthService s){service=s;}
 @PostMapping("/register") public void register(@RequestBody RegisterRequest r){service.register(r);}
 @PostMapping("/verify") public void verify(@RequestBody VerifyRequest r){service.verify(r);}
 @PostMapping("/login") public Map<String,String> login(@RequestBody LoginRequest r){
  return Map.of("message",service.login(r));
 }
 @GetMapping("/health") public String health(){return "OK";}
}

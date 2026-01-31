
package com.satwik.auth.service;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.satwik.auth.repository.UserRepository;
import com.satwik.auth.model.User;
import com.satwik.auth.dto.*;
@Service
public class AuthService {
 private final UserRepository repo;
 private final EmailService email;
 private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
 public AuthService(UserRepository r,EmailService e){repo=r;email=e;}
 public void register(RegisterRequest req){
  User u=new User();
  u.setUsername(req.username);
  u.setEmail(req.email);
  u.setPasswordHash(encoder.encode(req.password));
  u.setVerificationCode(""+(int)(Math.random()*900000+100000));
  u.setVerified(false);
  repo.save(u);
  email.send(u.getEmail(),u.getVerificationCode());
 }
 public void verify(VerifyRequest req){
  User u=repo.findByEmail(req.email).orElseThrow();
  if(!u.getVerificationCode().equals(req.code)) throw new RuntimeException("Invalid");
  u.setVerified(true);
  repo.save(u);
 }
 public String login(LoginRequest req){
  User u=repo.findByUsername(req.username).orElseThrow();
  if(!u.isVerified()) throw new RuntimeException("Not verified");
  if(!encoder.matches(req.password,u.getPasswordHash())) throw new RuntimeException("Wrong password");
  return "Hi "+u.getUsername()+", Welcome to the beautiful world";
 }
}

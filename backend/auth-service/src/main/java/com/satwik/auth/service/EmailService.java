
package com.satwik.auth.service;
import org.springframework.stereotype.Service;
@Service
public class EmailService {
 public void send(String email,String code){
  System.out.println("OTP "+code+" sent to "+email);
 }
}

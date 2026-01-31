
package com.satwik.auth.model;
import jakarta.persistence.*;
@Entity
@Table(name="users")
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 @Column(unique=true) private String username;
 @Column(unique=true) private String email;
 private String passwordHash;
 private boolean verified;
 private String verificationCode;
 public String getUsername(){return username;}
 public void setUsername(String u){this.username=u;}
 public String getEmail(){return email;}
 public void setEmail(String e){this.email=e;}
 public String getPasswordHash(){return passwordHash;}
 public void setPasswordHash(String p){this.passwordHash=p;}
 public boolean isVerified(){return verified;}
 public void setVerified(boolean v){this.verified=v;}
 public String getVerificationCode(){return verificationCode;}
 public void setVerificationCode(String c){this.verificationCode=c;}
}

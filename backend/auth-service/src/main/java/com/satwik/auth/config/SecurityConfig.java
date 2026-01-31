
package com.satwik.auth.config;
import org.springframework.context.annotation.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
@Configuration
public class SecurityConfig {
 @Bean
 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  http.csrf(c->c.disable()).authorizeHttpRequests(a->a.anyRequest().permitAll());
  return http.build();
 }
}

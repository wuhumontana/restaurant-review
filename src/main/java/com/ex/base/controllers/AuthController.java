package com.ex.base.controllers;

import com.ex.base.entity.User;
import com.ex.base.jpa.ApiUserCredentials;
import com.ex.base.jpa.ApiUserRepository;
import com.ex.base.security.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private ApiUserRepository userRepo;
    @Autowired private JWTUtility jwtu;
    @Autowired private AuthenticationManager authManager;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user = userRepo.save(user);

        String token = jwtu.generateToken(user.getEmail());
        return Collections.singletonMap("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody ApiUserCredentials body){
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtu.generateToken(body.getEmail());

            return Collections.singletonMap("jwt-token", token); // Returns JWT
        }catch (AuthenticationException authExc){
            throw new RuntimeException("Invalid Login Credentials");
        }
    }


}

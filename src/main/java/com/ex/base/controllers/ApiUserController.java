package com.ex.base.controllers;

import com.ex.base.entity.User;
import com.ex.base.jpa.ApiUserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    @Autowired private ApiUserRepository userRepo;

    @GetMapping("/info")
    public Optional<User> getUserDetails() {
        try {
            String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return Optional.of(userRepo.findByEmail(email).get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

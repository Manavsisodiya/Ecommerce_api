package com.manav.e_commerce_api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manav.e_commerce_api.dto.LoginRequest;
import com.manav.e_commerce_api.entity.User;
import com.manav.e_commerce_api.repository.UserRepository;
import com.manav.e_commerce_api.security.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginRequest loginRequest) {
        
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("This login system is for authorised admins only"));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            return jwtUtils.generateToken(user.getUsername(), authorities);
        } else {
            return "This login system is for authorised admins only";
        }
    }
}
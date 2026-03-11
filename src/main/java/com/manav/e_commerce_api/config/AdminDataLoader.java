package com.manav.e_commerce_api.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.manav.e_commerce_api.entity.User;
import com.manav.e_commerce_api.repository.UserRepository;

@Component
public class AdminDataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            
            admin.setPassword(passwordEncoder.encode("Manavsinh_17"));
            admin.setRoles(Collections.singleton("ROLE_ADMIN"));
            userRepository.save(admin);
            System.out.println("Admin user created: username=admin, password=admin123");
        }
    }
}
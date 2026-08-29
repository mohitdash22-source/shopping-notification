package com.mohit.shoppingnotification.service;

import com.mohit.shoppingnotification.dto.AuthRequestDTO;
import com.mohit.shoppingnotification.dto.LoginRequestDTO;
import com.mohit.shoppingnotification.model.User;
import com.mohit.shoppingnotification.repository.UserRepository;
import com.mohit.shoppingnotification.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User register(AuthRequestDTO request) {

        User user = new User();

        user.setUsername(request.getUsername());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole("USER");

        return userRepository.save(user);
    }

    public String login(LoginRequestDTO request) {

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid username or password");
        }

        return jwtService.generateToken(user.getUsername());
    }
}
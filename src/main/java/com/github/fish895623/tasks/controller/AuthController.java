package com.github.fish895623.tasks.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fish895623.tasks.entity.UserEntity;
import com.github.fish895623.tasks.enummerate.RoleEnum;
import com.github.fish895623.tasks.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(HttpSession session, @RequestBody UserEntity user) {
        log.info("Login attempt received: {}", user);

        if (user == null) {
            log.error("Login request body is null");
            throw new RuntimeException("Login request body is required");
        }

        String email = user.getEmail();
        String password = user.getPassword();

        if (email == null || email.trim().isEmpty()) {
            log.error("Email is required");
            throw new RuntimeException("Email is required");
        }

        if (password == null || password.trim().isEmpty()) {
            log.error("Password is required");
            throw new RuntimeException("Password is required");
        }

        log.info("Looking up user with email: {}", email);
        UserEntity foundUser = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("User not found for email: {}", email);
                    return new RuntimeException("User not found");
                });

        log.info("User found: {}", foundUser);
        session.setAttribute("user", foundUser);

        return ResponseEntity.ok(foundUser);
    }

    @GetMapping("/me")
    public ResponseEntity<UserEntity> me(HttpSession session) {
        UserEntity user;
        user = (UserEntity) session.getAttribute("user");

        if (user == null) {
            user = new UserEntity();
            user.setEmail("anonymous");
            user.setRole(RoleEnum.ANONYMOUS);
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return ResponseEntity.ok("Logged out");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserEntity user) {
        log.info("register: {}", user.toString());
        userRepository.save(user);
        return ResponseEntity.ok("Registered");
    }
}

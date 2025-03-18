package com.github.fish895623.tasks.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fish895623.tasks.entity.UserEntity;
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
    public ResponseEntity<String> login(HttpSession session) {
        UserEntity user = userRepository.findByEmail("dan990429@gmail.com")
                .orElseThrow(() -> new RuntimeException("User not found"));
        session.setAttribute("user", user);

        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/me")
    public ResponseEntity<UserEntity> me(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");

        return ResponseEntity.ok(user != null ? user : null);
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

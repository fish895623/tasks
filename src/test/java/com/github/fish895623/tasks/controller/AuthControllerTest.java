package com.github.fish895623.tasks.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fish895623.tasks.entity.UserEntity;
import com.github.fish895623.tasks.enummerate.RoleEnum;
import com.github.fish895623.tasks.service.UserService;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
@Transactional
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        testUser = new UserEntity();
        testUser.setEmail("test@example.com");
        testUser.setPassword("password123");
        testUser.setRole(RoleEnum.USER);
    }

    @Test
    void register_ShouldCreateNewUser() throws Exception {
        mockMvc.perform(post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(content().string("Registered"));
    }

    @Test
    void login() throws Exception {
        userService.register(testUser);

        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .session(session)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk());
    }

    @Test
    void login_WithoutUser_ShouldReturn401() throws Exception {
        userService.register(testUser);

        testUser.setEmail(null);

        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .session(session)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void login_invalid_email() throws Exception {
        userService.register(testUser);

        testUser.setEmail("");

        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .session(session)
                .content(objectMapper.writeValueAsString(testUser)));
    }

    @Test
    void login_invalid_password() throws Exception {
        userService.register(testUser);

        testUser.setPassword("");

        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .session(session)
                .content(objectMapper.writeValueAsString(testUser)));
    }

    @Test
    void login_user_not_found_throws_exception() throws Exception {
        userService.register(testUser);

        testUser.setEmail("nonexistent@example.com");

        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .session(session)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> {
                    Exception exception = result.getResolvedException();
                    assertTrue(exception instanceof RuntimeException);
                    assertEquals("User not found", exception.getMessage());
                });
    }
}

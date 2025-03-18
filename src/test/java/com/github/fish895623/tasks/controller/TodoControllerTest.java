package com.github.fish895623.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fish895623.tasks.entity.ProjectEntity;
import com.github.fish895623.tasks.repository.ProjectRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
@Transactional
@Rollback
public class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private ProjectEntity testProject;

}

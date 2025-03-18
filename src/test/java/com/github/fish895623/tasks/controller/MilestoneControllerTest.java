package com.github.fish895623.tasks.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.github.fish895623.tasks.entity.MilestoneEntity;
import com.github.fish895623.tasks.entity.ProjectEntity;
import com.github.fish895623.tasks.repository.ProjectRepository;
import com.github.fish895623.tasks.service.MilestoneService;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
@Transactional
@Rollback
public class MilestoneControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MilestoneService milestoneService;
    @Autowired
    private ProjectRepository projectRepository;

    private MilestoneEntity testMilestone;

    @BeforeEach
    void setUp() {
        // Create example Project
        ProjectEntity project = new ProjectEntity();
        project.setTitle("Test Project");
        project = projectRepository.save(project);

        testMilestone = new MilestoneEntity();
        testMilestone.setTitle("Test Milestone");
        testMilestone.setProject(project);
        testMilestone = milestoneService.save(testMilestone);
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/milestones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Milestone"));
    }
}

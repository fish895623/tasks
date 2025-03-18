package com.github.fish895623.tasks.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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

import com.github.fish895623.tasks.entity.ProjectEntity;
import com.github.fish895623.tasks.entity.TodoEntity;
import com.github.fish895623.tasks.repository.ProjectRepository;
import com.github.fish895623.tasks.repository.TodoRepository;

@SpringBootTest
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
    private TodoRepository todoRepository;

    private ProjectEntity testProject;
    private ProjectEntity emptyProject;

    @BeforeEach
    void setUp() {
        // Create test project with todos
        testProject = ProjectEntity.builder().title("Test Project").build();
        testProject = projectRepository.save(testProject);

        // Create todos for the test project
        TodoEntity todo1 = TodoEntity.builder()
                .title("First Todo")
                .description("First todo description")
                .completed(false)
                .project(testProject)
                .build();

        TodoEntity todo2 = TodoEntity.builder()
                .title("Second Todo")
                .description("Second todo description")
                .completed(true)
                .project(testProject)
                .build();

        todoRepository.save(todo1);
        todoRepository.save(todo2);

        // Create an empty project with no todos
        emptyProject = ProjectEntity.builder().title("Empty Project").build();
        emptyProject = projectRepository.save(emptyProject);
    }

    @Test
    void findByProjectId_shouldReturnTodos_whenProjectHasTodos() throws Exception {
        mockMvc.perform(get("/api/todos/projects/{id}", testProject.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("First Todo")))
                .andExpect(jsonPath("$[1].title", is("Second Todo")));
    }

    @Test
    void findByProjectId_shouldReturnEmptyList_whenProjectHasNoTodos() throws Exception {
        mockMvc.perform(get("/api/todos/projects/{id}", emptyProject.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void findByProjectId_shouldReturnEmptyList_whenProjectDoesNotExist() throws Exception {
        Long nonExistentId = 999L;
        mockMvc.perform(get("/api/todos/projects/{id}", nonExistentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}

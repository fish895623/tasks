package com.github.fish895623.tasks.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fish895623.tasks.entity.ProjectEntity;
import com.github.fish895623.tasks.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Slf4j
@RestController
public class ProjectController {
    private final ProjectRepository projectRepository;

    @GetMapping
    public List<ProjectEntity> getProjects() {
        log.info("getProjects");
        return projectRepository.findAll();
    }

    @PostMapping
    public ProjectEntity addProject(@RequestBody ProjectEntity project) {
        log.info("addProject: {}", project);
        return projectRepository.save(project);
    }

    @GetMapping("/{id}")
    public ProjectEntity getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }
}

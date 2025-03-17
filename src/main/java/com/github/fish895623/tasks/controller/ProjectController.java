package com.github.fish895623.tasks.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.fish895623.tasks.entity.ProjectEntity;
import com.github.fish895623.tasks.service.ProjectService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public List<ProjectEntity> getProjects() {
        return projectService.getProjects();
    }

    @PostMapping
    public ProjectEntity addProject(@RequestBody ProjectEntity project) {
        projectService.addProject(project.getTitle());

        return project;
    }

    @GetMapping("/{id}")
    public ProjectEntity getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }
}

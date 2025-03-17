package com.github.fish895623.tasks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.fish895623.tasks.entity.ProjectEntity;
import com.github.fish895623.tasks.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public void addProject(String title) {
        var project = new ProjectEntity();
        project.setTitle(title);

        projectRepository.save(project);
    }

    public List<ProjectEntity> getProjects() {
        return projectRepository.findAll();
    }

    public ProjectEntity getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }
}

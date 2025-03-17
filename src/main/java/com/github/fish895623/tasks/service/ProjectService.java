package com.github.fish895623.tasks.service;

import org.springframework.stereotype.Service;

import com.github.fish895623.tasks.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public void addProject(String title) {
    }
}

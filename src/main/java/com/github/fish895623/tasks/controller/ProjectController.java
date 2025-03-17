package com.github.fish895623.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.fish895623.tasks.service.ProjectService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public String getProjects() {
        return "projects";
    }

    @PostMapping
    public String addProject(@RequestParam(required = true, name = "title") String title) {
        log.info("addProject: {}", title);
        return "redirect:/projects";
    }
}

package com.github.fish895623.tasks.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fish895623.tasks.entity.MilestoneEntity;
import com.github.fish895623.tasks.service.MilestoneService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/milestones")
@RequiredArgsConstructor
public class MilestoneController {
    private final MilestoneService milestoneService;

    @GetMapping
    public List<MilestoneEntity> findAll() {
        return milestoneService.getMilestones();
    }
}
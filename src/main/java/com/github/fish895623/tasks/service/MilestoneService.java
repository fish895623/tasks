package com.github.fish895623.tasks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.fish895623.tasks.entity.MilestoneEntity;
import com.github.fish895623.tasks.repository.MilestoneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;

    public List<MilestoneEntity> getMilestones() {
        return milestoneRepository.findAll();
    }

    public MilestoneEntity save(MilestoneEntity milestone) {
        return milestoneRepository.save(milestone);
    }
}

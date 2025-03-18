package com.github.fish895623.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.fish895623.tasks.entity.MilestoneEntity;

public interface MilestoneRepository extends JpaRepository<MilestoneEntity, Long> {
}

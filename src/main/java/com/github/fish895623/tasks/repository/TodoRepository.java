package com.github.fish895623.tasks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.fish895623.tasks.entity.ProjectEntity;
import com.github.fish895623.tasks.entity.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findByProject(ProjectEntity project);
}

package com.github.fish895623.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.fish895623.tasks.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}

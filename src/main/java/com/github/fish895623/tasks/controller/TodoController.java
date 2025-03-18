package com.github.fish895623.tasks.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fish895623.tasks.entity.TodoEntity;
import com.github.fish895623.tasks.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public List<TodoEntity> findAll() {
        return todoService.findAll();
    }

    @GetMapping("/projects/{id}")
    public List<TodoEntity> findByPorjectId(@PathVariable(name = "id") Long id) {
        return todoService.findByProjectId(id);
    }
}
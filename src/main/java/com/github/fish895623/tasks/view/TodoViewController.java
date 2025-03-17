package com.github.fish895623.tasks.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.fish895623.tasks.entity.TodoEntity;
import com.github.fish895623.tasks.service.TodoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoViewController {
    private final TodoService todoService;

    @GetMapping
    public String getAllTodos() {
        return "index";
    }

    @PostMapping
    public String addTodo(@RequestParam String title) {
        todoService.addTodo(title);
        return "redirect:/todos";
    }

    @GetMapping("/{id}")
    public TodoEntity getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

}

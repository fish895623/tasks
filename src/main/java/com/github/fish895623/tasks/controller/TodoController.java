package com.github.fish895623.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.fish895623.tasks.entity.Todo;
import com.github.fish895623.tasks.service.TodoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public String getAllTodos(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        return "index";
    }

    @PostMapping
    public String addTodo(@RequestParam String title) {
        todoService.addTodo(title);
        return "redirect:/todos";
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

}

package com.github.fish895623.tasks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.fish895623.tasks.entity.Todo;
import com.github.fish895623.tasks.repository.TodoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo not found with id: " + id));
    }

    public void addTodo(String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todoRepository.save(todo);
    }

}

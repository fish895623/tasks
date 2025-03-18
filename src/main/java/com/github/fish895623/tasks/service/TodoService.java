package com.github.fish895623.tasks.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.fish895623.tasks.entity.ProjectEntity;
import com.github.fish895623.tasks.entity.TodoEntity;
import com.github.fish895623.tasks.repository.ProjectRepository;
import com.github.fish895623.tasks.repository.TodoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final ProjectRepository projectRepository;

    public List<TodoEntity> findAll() {
        return todoRepository.findAll();
    }

    public List<TodoEntity> findByProjectId(Long projectId) {
        Optional<ProjectEntity> project = projectRepository.findById(projectId);

        if (project.isEmpty()) {
            List<TodoEntity> todos = new ArrayList<>();
            return todos;
        }

        return todoRepository.findByProject(project.get());
    }

    public TodoEntity getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo not found with id: " + id));
    }

    public void addTodo(String title) {
        TodoEntity todo = new TodoEntity();
        todo.setTitle(title);
        todoRepository.save(todo);
    }

}

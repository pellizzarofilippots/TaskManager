package com.taskm.task_manager.service;

import com.taskm.task_manager.model.Task;
import com.taskm.task_manager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Task create(Task task) {
        return repository.save(task);
    }

    public Task update(Long id, Task task) {
        Task existing = getById(id);
        existing.setTitle(task.getTitle());
        existing.setDescription(task.getDescription());
        existing.setCompleted(task.isCompleted());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
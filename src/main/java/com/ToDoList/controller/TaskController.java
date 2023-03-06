package com.ToDoList.controller;

import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDTO getTask(@PathVariable(value = "id") Long id) {
        return taskService.findTaskById(id);
    }

    @PostMapping
    public void createTask(@RequestBody TaskDTO taskDTO) {
        taskService.addTask(taskDTO);
    }

    @PutMapping("/{id}")
    public void editTask(@RequestBody TaskDTO taskDTO, @PathVariable(value = "id") Long id) {
        taskService.editTask(taskDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(value = "id") Long id) {
        taskService.deleteTask(id);
    }
}

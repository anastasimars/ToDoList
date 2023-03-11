package com.ToDoList.controller;

import com.ToDoList.model.repository.TaskRepository;
import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.service.ToDoAPI;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "Task Controller", description = "Interaction with tasks")
public class TaskController {
    ToDoAPI toDoAPI;
    @GetMapping(value = "/tasks")
    public List<TaskDTO> getAllTasks() {
       return toDoAPI.getAllTasks();
    }

    @PostMapping(value = "/tasks")
    public void addTask(@RequestBody TaskDTO taskDTO) {
        throw new RuntimeException("No implementation");
    }

    @PutMapping("/tasks/{id}")
    public void updateTask(@RequestBody TaskDTO taskDTO,
                           @PathVariable Long id) {
        throw new RuntimeException("No implementation");
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        throw new RuntimeException("No implementation");
    }

    @GetMapping("/tasks/{id}/subtasks")
    public TaskDTO getAllWithSubtasks() {
        throw new RuntimeException("No implementation");
    }

    @PostMapping("/tasks/{id}/subtasks")
    public void addSubtask(@PathVariable Long id,
                           @RequestBody SubtaskDTO subtaskDTO) {
        throw new RuntimeException("No implementation");
    }

    @GetMapping("/subtasks/{id}")
    public SubtaskDTO getSubtaskById(@PathVariable Long id) {
        throw new RuntimeException("No implementation");
    }

    @PutMapping("/subtasks/{id}")
    public void editSubtaskById(@PathVariable Long id) {
        throw new RuntimeException("No implementation");
    }

    @DeleteMapping("/subtasks/{id}")
    public void deleteSubtaskById(@PathVariable Long id){
        throw new RuntimeException("No implementation");
    }
}

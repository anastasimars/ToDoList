package com.ToDoList.controller;

import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.service.logic.ToDoAPI;
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
        toDoAPI.addTask(taskDTO);
    }

    @PutMapping("/tasks/{id}")
    public void updateTask(@RequestBody TaskDTO taskDTO,
                           @PathVariable Long id) {
        toDoAPI.updateTask(taskDTO, id);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        toDoAPI.deleteTask(id);
    }

    @GetMapping("/tasks/{id}/subtasks")
    public List<SubtaskDTO> getAllSubtasksByTaskId(@PathVariable Long id) {
       return toDoAPI.getAllSubtasksByTaskId(id);
    }

    @PostMapping("/tasks/{id}/subtasks")
    public void addSubtask(@PathVariable Long id,
                           @RequestBody SubtaskDTO subtaskDTO) {
        toDoAPI.addSubtask(id, subtaskDTO);
    }

    @PutMapping("/subtasks/{id}")
    public void editSubtaskById(@PathVariable Long id) {
        toDoAPI.editSubtask(id);
    }

    @DeleteMapping("/subtasks/{id}")
    public void deleteSubtaskById(@PathVariable Long id){
        toDoAPI.deleteSubtask(id);
    }
}

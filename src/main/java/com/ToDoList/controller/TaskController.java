package com.ToDoList.controller;

import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.repository.entity.SubtaskEntity;
import com.ToDoList.model.repository.entity.TaskEntity;
import com.ToDoList.model.service.logic.ToDoAPI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
class TaskController {
    private final ToDoAPI toDoAPI;
    @GetMapping(value = "/tasks")
    public List<TaskDTO> getAllTasks() {
       return toDoAPI.getAllTasks();
    }

    @GetMapping(value = "/tasks/{id}")
    public TaskEntity getTask(@PathVariable Long id){
        return toDoAPI.getTask(id);
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

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<TaskEntity> markTaskAsCompleted (@PathVariable Long id){
        TaskEntity taskEntity = toDoAPI.markTaskAsCompleted(id);
        return ResponseEntity.ok(taskEntity);
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
    public void editSubtaskById(@PathVariable Long id, @RequestBody SubtaskDTO subtaskDTO) {
        toDoAPI.editSubtask(id, subtaskDTO);
    }

    @DeleteMapping("/subtasks/{id}")
    public void deleteSubtaskById(@PathVariable Long id){
        toDoAPI.deleteSubtask(id);
    }

    @PatchMapping ("/subtasks/{id}")
    public ResponseEntity<SubtaskEntity> markSubtaskAsCompleted(@PathVariable Long id) {
        SubtaskEntity subtaskEntity = toDoAPI.markSubtaskAsCompleted(id);
        return ResponseEntity.ok(subtaskEntity);
    }
}

package com.ToDoList.controller;

import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.repository.entity.SubtaskEntity;
import com.ToDoList.model.service.logic.ToDoAPI;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> allTasks = toDoAPI.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping(value = "/tasks/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long id){
        TaskDTO task = toDoAPI.getTask(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping(value = "/tasks")
    public ResponseEntity<Void> addTask(@RequestBody TaskDTO taskDTO) {
        toDoAPI.addTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Void> updateTask(@RequestBody TaskDTO taskDTO,
                           @PathVariable Long id) {
        toDoAPI.updateTask(taskDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        toDoAPI.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<TaskDTO> markTaskAsCompleted (@PathVariable Long id){
        TaskDTO taskDTO = toDoAPI.markTaskAsCompleted(id);
        return ResponseEntity.ok(taskDTO);
    }

    @GetMapping("/tasks/{id}/subtasks")
    public ResponseEntity<List<SubtaskDTO>> getAllSubtasksByTaskId(@PathVariable Long id) {
        List<SubtaskDTO> subtasks = toDoAPI.getAllSubtasksByTaskId(id);
        return ResponseEntity.ok(subtasks);
    }

    @PostMapping("/tasks/{id}/subtasks")
    public ResponseEntity<Void> addSubtask(@PathVariable Long id,
                                             @RequestBody SubtaskDTO subtaskDTO) {
        toDoAPI.addSubtask(id, subtaskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/subtasks/{id}")
    public ResponseEntity<Void> editSubtaskById(@PathVariable Long id, @RequestBody SubtaskDTO subtaskDTO) {
        toDoAPI.editSubtask(id, subtaskDTO);
       return ResponseEntity.ok().build();
    }

    @DeleteMapping("/subtasks/{id}")
    public ResponseEntity<Void> deleteSubtaskById(@PathVariable Long id){
        toDoAPI.deleteSubtask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping ("/subtasks/{id}")
    public ResponseEntity<SubtaskEntity> markSubtaskAsCompleted(@PathVariable Long id) {
        SubtaskEntity subtaskEntity = toDoAPI.markSubtaskAsCompleted(id);
        return ResponseEntity.ok(subtaskEntity);
    }
}

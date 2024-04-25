package com.todolist.controller;

import com.todolist.model.repository.dto.SubtaskDTO;
import com.todolist.model.repository.dto.TaskDTO;
import com.todolist.model.repository.entity.SubtaskEntity;
import com.todolist.model.service.logic.ToDoAPI;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
class TaskController {
    private final ToDoAPI toDoAPI;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> allTasks = toDoAPI.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping(value = "/{taskId}")
    public ResponseEntity<TaskDTO> getTaskWithSubtasks(@PathVariable Long id) {
        TaskDTO task = toDoAPI.findTaskWithSubtasks(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Void> addTask(@RequestBody TaskDTO taskDTO) {
        toDoAPI.addTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(@RequestBody TaskDTO taskDTO,
                                           @PathVariable Long id) {
        toDoAPI.updateTask(taskDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        toDoAPI.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskDTO> markTaskAsCompleted(@PathVariable Long id) {
        TaskDTO taskDTO = toDoAPI.markTaskAsCompleted(id);
        return ResponseEntity.ok(taskDTO);
    }

    @GetMapping("/{taskId}/subtasks")
    public ResponseEntity<List<SubtaskDTO>> getAllSubtasksByTaskId(@PathVariable Long id) {
        List<SubtaskDTO> subtasks = toDoAPI.getAllSubtasksByTaskId(id);
        return ResponseEntity.ok(subtasks);
    }

    @PostMapping("/{id}/subtasks")
    public ResponseEntity<Void> addSubtask(@PathVariable Long id,
                                           @RequestBody SubtaskDTO subtaskDTO) {
        toDoAPI.addSubtask(id, subtaskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{taskId}/subtasks/{subtaskId}")
    public ResponseEntity<Void> editSubtaskById(@PathVariable Long id, @RequestBody SubtaskDTO subtaskDTO) {
        toDoAPI.editSubtask(id, subtaskDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}/subtasks/{subtaskId}")
    public ResponseEntity<Void> deleteSubtaskById(@PathVariable Long id) {
        toDoAPI.deleteSubtask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{taskId}/subtasks/{subtaskId}")
    public ResponseEntity<SubtaskEntity> markSubtaskAsCompleted(@PathVariable Long id) {
        SubtaskEntity subtaskEntity = toDoAPI.markSubtaskAsCompleted(id);
        return ResponseEntity.ok(subtaskEntity);
    }
}

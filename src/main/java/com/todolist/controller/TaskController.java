package com.todolist.controller;

import com.todolist.model.repository.dto.SubtaskDtoRequest;
import com.todolist.model.repository.dto.SubtaskDtoResponse;
import com.todolist.model.repository.dto.TaskDtoRequest;
import com.todolist.model.repository.dto.TaskDtoResponse;
import com.todolist.model.repository.entity.SubtaskEntity;
import com.todolist.model.service.logic.ToDoAPI;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
class TaskController {
    private final ToDoAPI toDoAPI;

    @GetMapping
    public ResponseEntity<List<TaskDtoResponse>> getAllTasks() {
        List<TaskDtoResponse> allTasks = toDoAPI.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping(value = "/{taskId}")
    public ResponseEntity<TaskDtoResponse> getTask(@PathVariable UUID taskId) {
        TaskDtoResponse task = toDoAPI.findTaskWithSubtasks(taskId);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Void> addTask(@RequestBody TaskDtoRequest taskDtoRequest) {
        toDoAPI.addTask(taskDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(@RequestBody TaskDtoRequest taskDtoRequest,
                                           @PathVariable UUID taskId) {
        toDoAPI.editTask(taskDtoRequest, taskId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        toDoAPI.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<TaskDtoResponse> markTaskAsCompleted(@PathVariable UUID taskId) {
        TaskDtoResponse taskDtoResponse = toDoAPI.markTaskAsCompleted(taskId);
        return ResponseEntity.ok(taskDtoResponse);
    }

    @PostMapping("/{id}/subtasks")
    public ResponseEntity<Void> addSubtask(@PathVariable UUID taskId,
                                           @RequestBody SubtaskDtoRequest subtaskDtoRequest) {
        toDoAPI.addSubtask(taskId, subtaskDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{taskId}/subtasks/{subtaskId}")
    public ResponseEntity<Void> editSubtaskById(@PathVariable UUID subtaskId,
                                                @RequestBody SubtaskDtoRequest subtaskDtoRequest,
                                                @RequestBody boolean status) {
        toDoAPI.editSubtask(subtaskId, subtaskDtoRequest, status);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}/subtasks/{subtaskId}")
    public ResponseEntity<Void> deleteSubtaskById(@PathVariable UUID taskId,
                                                  @PathVariable UUID subtaskId) {
        toDoAPI.deleteSubtask(taskId, subtaskId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{taskId}/subtasks/{subtaskId}/complete")
    public ResponseEntity<SubtaskEntity> markSubtaskAsCompleted(@PathVariable UUID subtaskId,
                                                                @RequestBody boolean status) {
        SubtaskEntity subtaskEntity = toDoAPI.markSubtaskAsCompleted(subtaskId, status);
        return ResponseEntity.ok(subtaskEntity);
    }
}

package com.todolist.controller;

import com.todolist.model.repository.dto.SubtaskDto;
import com.todolist.model.repository.dto.TaskDto;
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
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> allTasks = toDoAPI.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping(value = "/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable UUID taskId) {
        TaskDto task = toDoAPI.findTaskWithSubtasks(taskId);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Void> addTask(@RequestBody TaskDto taskDto) {
        toDoAPI.addTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(@RequestBody TaskDto taskDto,
                                           @PathVariable UUID taskId) {
        toDoAPI.editTask(taskDto, taskId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        toDoAPI.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<TaskDto> markTaskAsCompleted(@PathVariable UUID taskId) {
        TaskDto taskDto = toDoAPI.markTaskAsCompleted(taskId);
        return ResponseEntity.ok(taskDto);
    }

    @PostMapping("/{id}/subtasks")
    public ResponseEntity<Void> addSubtask(@PathVariable UUID taskId,
                                           @RequestBody SubtaskDto subtaskDto) {
        toDoAPI.addSubtask(taskId, subtaskDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{taskId}/subtasks/{subtaskId}")
    public ResponseEntity<Void> editSubtaskById(@PathVariable UUID subtaskId,
                                                @RequestBody SubtaskDto subtaskDto) {
        toDoAPI.editSubtask(subtaskId, subtaskDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}/subtasks/{subtaskId}")
    public ResponseEntity<Void> deleteSubtaskById(@PathVariable UUID taskId,
                                                  @PathVariable UUID subtaskId) {
        toDoAPI.deleteSubtask(taskId, subtaskId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{taskId}/subtasks/{subtaskId}/complete")
    public ResponseEntity<SubtaskEntity> markSubtaskAsCompleted(@PathVariable UUID subtaskId) {
        SubtaskEntity subtaskEntity = toDoAPI.markSubtaskAsCompleted(subtaskId);
        return ResponseEntity.ok(subtaskEntity);
    }
}

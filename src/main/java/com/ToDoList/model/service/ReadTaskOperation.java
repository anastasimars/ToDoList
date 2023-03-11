package com.ToDoList.model.service;

import com.ToDoList.model.repository.SubtaskRepository;
import com.ToDoList.model.repository.TaskRepository;
import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.service.mapping.SubtaskMapper;
import com.ToDoList.model.service.mapping.TaskMapper;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class ReadTaskOperation {
    private final TaskRepository taskRepository;
    private final SubtaskRepository subtaskRepository;
    private final TaskMapper taskMapper;
    private final SubtaskMapper subtaskMapper;

    List<TaskDTO> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::fromEntity)
                .toList();
    }
}

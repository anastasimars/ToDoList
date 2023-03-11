package com.ToDoList.model.service.logic;

import com.ToDoList.model.repository.SubtaskRepository;
import com.ToDoList.model.repository.TaskRepository;
import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.repository.entity.Task;
import com.ToDoList.model.service.mapping.SubtaskMapper;
import com.ToDoList.model.service.mapping.TaskMapper;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class TaskOperations {
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

    void addTask(TaskDTO taskDTO){
        Task task = taskMapper.toEntity(taskDTO);
        taskRepository.save(task);
    }


}

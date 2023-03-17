package com.ToDoList.model.service.logic;

import com.ToDoList.model.repository.TaskRepository;
import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.repository.entity.Task;
import com.ToDoList.model.service.mapping.TaskMapper;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
class TaskOperations {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    List<TaskDTO> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::fromEntity)
                .toList();
    }

    void addTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        taskRepository.save(task);
    }

    void updateTask(TaskDTO taskDTO, Long id) {
        taskRepository.findById(id)
                .map(task -> {
                    task.setTaskTitle(taskDTO.getTaskTitle());
                    task.setDeadline(taskDTO.getDeadline());
                    task.setStatus(taskDTO.isStatus());
                    return task;
                })
                .ifPresent(taskRepository::save);
    }

    void deleteTask(Long id) {
        Optional<Task> findTaskByID = taskRepository.findById(id);
        Task deletedTask = findTaskByID.orElseThrow(() -> new NotFoundException(id));
        taskRepository.delete(deletedTask);
    }

    Task markTaskAsCompleted(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException(taskId));
        task.setStatus(true);
        return taskRepository.save(task);
    }
}

package com.ToDoList.model.service.logic;

import com.ToDoList.model.repository.TaskRepository;
import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.repository.entity.TaskEntity;
import com.ToDoList.model.service.logic.exceptions.TaskNotFoundException;
import com.ToDoList.model.service.mapping.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    TaskDTO getTaskWithSubtasks(Long taskId) {
        TaskEntity taskWithSubtasksById = taskRepository
                .findTaskWithSubtasksById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
        return taskMapper.fromEntity(taskWithSubtasksById);
    }

    @Transactional
    void addTask(TaskDTO taskDTO) {
        TaskEntity taskEntity = taskMapper.toEntity(taskDTO);
        taskRepository.save(taskEntity);
    }

    @Transactional
    void updateTask(TaskDTO taskDTO, Long id) {
        taskRepository.findById(id)
                .map(task -> {
                    task.updateTaskTitle(taskDTO.getTaskTitle());
                    task.updateDeadline(taskDTO.getDeadline());
                    task.updateStatus();
                    return task;
                })
                .ifPresent(taskRepository::save);
    }

    @Transactional
    void deleteTask(Long id) {
        Optional<TaskEntity> findTaskByID = taskRepository.findById(id);
        TaskEntity deletedTaskEntity = findTaskByID.orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(deletedTaskEntity);
    }

    @Transactional
    TaskDTO markTaskAsCompleted(Long taskId) {
        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
        taskEntity.updateStatus();
        taskRepository.save(taskEntity);
        return taskMapper.fromEntity(taskEntity);
    }


}

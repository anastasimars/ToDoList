package com.ToDoList.model.service.logic;

import com.ToDoList.model.repository.TaskRepository;
import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.repository.entity.TaskEntity;
import com.ToDoList.model.service.logic.exceptions.TaskNotFoundException;
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
        TaskEntity taskEntity = taskMapper.toEntity(taskDTO);
        taskRepository.save(taskEntity);
    }

    public TaskEntity getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
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
        Optional<TaskEntity> findTaskByID = taskRepository.findById(id);
        TaskEntity deletedTaskEntity = findTaskByID.orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(deletedTaskEntity);
    }

    TaskEntity markTaskAsCompleted(Long taskId) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        taskEntity.setStatus(true);
        return taskRepository.save(taskEntity);
    }


}

package com.todolist.model.service.logic;

import com.todolist.model.repository.SubtaskRepository;
import com.todolist.model.repository.TaskRepository;
import com.todolist.model.repository.dto.SubtaskDto;
import com.todolist.model.repository.dto.TaskDto;
import com.todolist.model.repository.entity.SubtaskEntity;
import com.todolist.model.repository.entity.TaskEntity;
import com.todolist.model.service.logic.exceptions.NotFoundException;
import com.todolist.model.service.mapping.SubtaskMapper;
import com.todolist.model.service.mapping.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
class ToDoService implements ToDoAPI {
    private final TaskRepository taskRepository;
    private final SubtaskRepository subtaskRepository;
    private final TaskMapper taskMapper;
    private final SubtaskMapper subtaskMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAllWithSubtasks()
                .stream()
                .map(taskMapper::fromEntity)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDto findTaskWithSubtasks(UUID taskId) {
        TaskEntity taskEntity = findTaskByTechId(taskId);
        return taskMapper.fromEntity(taskEntity);
    }


    @Override
    @Transactional
    public void addTask(TaskDto taskDto) {
        TaskEntity taskEntity = findTaskByTechId(taskDto.getTechId());
        taskEntity.getSubtasks().addAll(taskDto.getSubtasks()
                .stream()
                .map(subtaskMapper::toEntity)
                .peek(subtaskEntity -> subtaskEntity.joinTask(taskEntity))
                .toList());
        taskRepository.save(taskEntity);
    }

    @Override
    @Transactional
    public void editTask(TaskDto taskDto, UUID taskId) {
        TaskEntity task = findTaskByTechId(taskId);
        task.updateTaskTitle(taskDto.getTaskTitle());
        task.updateDeadline(taskDto.getDeadline());
        task.updateStatus();

        updateSubtasks(task, taskDto.getSubtasks());
        taskRepository.save(task);
    }

    private void updateSubtasks(TaskEntity task, List<SubtaskDto> subtasksDtoList) {
        List<UUID> updatedSubtaskIds = subtasksDtoList.stream()
                .map(SubtaskDto::getTechId)
                .toList();

        task.getSubtasks().removeIf(subtask ->
                !updatedSubtaskIds.contains(subtask.getTechId()));

        subtasksDtoList.forEach(dto -> {
            SubtaskEntity subtask = task.getSubtasks().stream()
                    .filter(s -> s.getTechId().equals(dto.getTechId()))
                    .findFirst()
                    .orElseGet(() -> {
                        SubtaskEntity newSubtask = subtaskMapper.toEntity(dto);
                        newSubtask.joinTask(task);
                        task.getSubtasks().add(newSubtask);
                        return newSubtask;
                    });
            subtask.updateSubtaskTitle(dto.getSubtaskTitle());
            subtask.updateStatus(dto.isStatus());
        });
    }

    @Override
    @Transactional
    public void deleteTask(UUID taskId) {
        TaskEntity taskEntity = findTaskByTechId(taskId);
        taskRepository.delete(taskEntity);
    }

    @Override
    @Transactional
    public TaskDto markTaskAsCompleted(UUID taskId) {
        TaskEntity taskEntity = findTaskByTechId(taskId);
        taskEntity.updateStatus();
        taskRepository.save(taskEntity);
        return taskMapper.fromEntity(taskEntity);
    }

    @Override
    @Transactional
    public void addSubtask(UUID taskId, SubtaskDto subtaskDto) {
        TaskEntity taskEntity = findTaskByTechId(taskId);
        SubtaskEntity subtask = subtaskMapper.toEntity(subtaskDto);
        subtask.joinTask(taskEntity);
        subtaskRepository.save(subtask);
    }

    @Override
    @Transactional
    public void editSubtask(UUID subtaskId, SubtaskDto subtaskDto) {
        SubtaskEntity subtaskEntity = findSubtaskByTechId(subtaskId);
        subtaskEntity.updateSubtaskTitle(subtaskDto.getSubtaskTitle());
        subtaskEntity.updateSubtaskStatus(subtaskDto.isStatus());
        subtaskRepository.save(subtaskEntity);
    }

    @Override
    public void deleteSubtask(UUID taskId, UUID subtaskId) {
        TaskEntity taskEntity = findTaskByTechId(taskId);
        SubtaskEntity subtaskEntity = taskEntity.getSubtasks().stream()
                .filter(s -> s.getTechId().equals(subtaskId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(subtaskId));
        taskEntity.getSubtasks().remove(subtaskEntity);
        taskRepository.save(taskEntity);
    }

    @Override
    public SubtaskEntity markSubtaskAsCompleted(UUID subtaskId) {
        SubtaskEntity subtaskEntity = subtaskRepository.findByTechId(subtaskId).orElseThrow(() -> new NotFoundException(subtaskId));
        subtaskEntity.updateStatus(true);
        return subtaskRepository.save(subtaskEntity);
    }

    private TaskEntity findTaskByTechId(UUID taskId) {
        return taskRepository
                .findByTechIdWithSubtasks(taskId)
                .orElseThrow(() -> new NotFoundException(taskId));
    }

    private SubtaskEntity findSubtaskByTechId(UUID subtaskId) {
        return subtaskRepository.findByTechId(subtaskId)
                .orElseThrow(() -> new NotFoundException(subtaskId));
    }
}

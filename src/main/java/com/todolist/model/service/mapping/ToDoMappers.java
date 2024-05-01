package com.todolist.model.service.mapping;

import com.todolist.model.repository.dto.SubtaskDtoRequest;
import com.todolist.model.repository.dto.SubtaskDtoResponse;
import com.todolist.model.repository.dto.TaskDtoRequest;
import com.todolist.model.repository.dto.TaskDtoResponse;
import com.todolist.model.repository.entity.SubtaskEntity;
import com.todolist.model.repository.entity.TaskEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ToDoMappers {

    public TaskDtoResponse fromTaskEntityToResponse(TaskEntity taskEntity) {
        List<SubtaskDtoResponse> subtaskDtos = toSubtaskDtoList(taskEntity.getSubtasks());
        return TaskDtoResponse.builder()
                .techId(taskEntity.getTechId())
                .taskTitle(taskEntity.getTaskTitle())
                .deadline(taskEntity.getDeadline())
                .status(taskEntity.isStatus())
                .subtasks(subtaskDtos)
                .build();
    }

    public TaskEntity fromTaskRequestToEntity(TaskDtoRequest taskRequest) {
        List<SubtaskEntity> subtaskEntities = toSubtaskEntityList(taskRequest.getSubtasks());
        return TaskEntity.builder()
                .taskTitle(taskRequest.getTaskTitle())
                .deadline(taskRequest.getDeadline())
                .subtasks(subtaskEntities)
                .build();
    }

    public List<SubtaskEntity> toSubtaskEntityList(List<SubtaskDtoRequest> subtasks) {
        return Optional.ofNullable(subtasks)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::fromSubtaskRequestToEntity)
                .toList();
    }

    public List<SubtaskDtoResponse> toSubtaskDtoList(List<SubtaskEntity> subtasks) {
        return Optional.ofNullable(subtasks)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::fromSubtaskEntityToResponse)
                .toList();
    }

    public SubtaskDtoResponse fromSubtaskEntityToResponse(SubtaskEntity entity) {
        return SubtaskDtoResponse.builder()
                .techId(entity.getTechId())
                .subtaskTitle(entity.getSubtaskTitle())
                .status(entity.isStatus())
                .build();
    }

    public SubtaskEntity fromSubtaskRequestToEntity(SubtaskDtoRequest request) {
        return SubtaskEntity.builder()
                .subtaskTitle(request.getSubtaskTitle())
                .task(null)
                .build();
    }
}

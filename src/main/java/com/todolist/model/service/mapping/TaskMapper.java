package com.todolist.model.service.mapping;

import com.todolist.model.repository.dto.SubtaskDto;
import com.todolist.model.repository.dto.TaskDto;
import com.todolist.model.repository.entity.SubtaskEntity;
import com.todolist.model.repository.entity.TaskEntity;

import java.util.List;


public class TaskMapper {

    public TaskDto fromEntity(TaskEntity taskEntity) {

        return TaskDto.builder()
                .techId(taskEntity.getTechId())
                .taskTitle(taskEntity.getTaskTitle())
                .deadline(taskEntity.getDeadline())
                .status(taskEntity.isStatus())
                .subtasks(toSubtaskDtoList(taskEntity.getSubtasks()))
                .build();
    }

    public TaskEntity toEntity(TaskDto taskDto) {
        return
                TaskEntity.builder()
                        .techId(taskDto.getTechId())
                        .taskTitle(taskDto.getTaskTitle())
                        .deadline(taskDto.getDeadline())
                        .status(taskDto.isStatus())
                        .subtasks(toSubtaskEntityList(taskDto.getSubtasks()))
                        .build();
    }

    private List<SubtaskEntity> toSubtaskEntityList(List<SubtaskDto> subtasks) {
        return subtasks.stream().map(this::toEntity)
                .toList();
    }

    private SubtaskEntity toEntity(SubtaskDto subtaskDto) {
        return SubtaskEntity.builder()
                .techId(subtaskDto.getTechId())
                .subtaskTitle(subtaskDto.getSubtaskTitle())
                .status(subtaskDto.isStatus())
                .build();
    }

    private SubtaskDto toDto(SubtaskEntity subtaskEntity) {
        return SubtaskDto.builder()
                .techId(subtaskEntity.getTechId())
                .subtaskTitle(subtaskEntity.getSubtaskTitle())
                .status(subtaskEntity.isStatus())
                .build();
    }

    public List<SubtaskDto> toSubtaskDtoList(List<SubtaskEntity> subtaskEntities) {
        return subtaskEntities.stream().map(this::toDto)
                .toList();
    }

}

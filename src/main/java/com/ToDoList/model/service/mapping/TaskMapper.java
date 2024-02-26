package com.ToDoList.model.service.mapping;

import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.repository.entity.SubtaskEntity;
import com.ToDoList.model.repository.entity.TaskEntity;

import java.util.List;


public class TaskMapper {

    public TaskDTO fromEntity(TaskEntity taskEntity) {

        return TaskDTO.builder()
                .taskTitle(taskEntity.getTaskTitle())
                .deadline(taskEntity.getDeadline())
                .status(taskEntity.isStatus())
                .subtasks(toSubtaskDtoList(taskEntity.getSubtasks()))
                .build();
    }

    public TaskEntity toEntity(TaskDTO taskDTO) {
        return TaskEntity.builder()
                .taskTitle(taskDTO.getTaskTitle())
                .deadline(taskDTO.getDeadline())
                .status(taskDTO.isStatus())
                .subtasks(toSubtaskEntityList(taskDTO.getSubtasks()))
                .build();
    }

    private List<SubtaskEntity> toSubtaskEntityList(List<SubtaskDTO> subtasks) {
        return subtasks.stream().map(this::toEntity)
                .toList();
    }

    private SubtaskEntity toEntity(SubtaskDTO subtaskDTO) {
        return SubtaskEntity.builder()
                .subtaskTitle(subtaskDTO.getSubtaskTitle())
                .status(subtaskDTO.isStatus())
                .build();
    }

    private SubtaskDTO toDto(SubtaskEntity subtaskEntity) {
        return SubtaskDTO.builder()
                .subtaskTitle(subtaskEntity.getSubtaskTitle())
                .status(subtaskEntity.isStatus())
                .build();
    }

    public List<SubtaskDTO> toSubtaskDtoList(List<SubtaskEntity> subtaskEntities) {
        return subtaskEntities.stream().map(this::toDto)
                .toList();
    }

}

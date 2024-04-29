package com.todolist.model.service.mapping;

import com.todolist.model.repository.dto.SubtaskDto;
import com.todolist.model.repository.entity.SubtaskEntity;

import java.util.UUID;


public class SubtaskMapper {

    public SubtaskDto fromEntity(SubtaskEntity subtaskEntity) {
        return SubtaskDto.builder()
                .techId(subtaskEntity.getTechId())
                .subtaskTitle(subtaskEntity.getSubtaskTitle())
                .status(subtaskEntity.isStatus())
                .build();
    }

    public SubtaskEntity toEntity(SubtaskDto subtaskDto) {
        return SubtaskEntity.builder()
                .techId(UUID.randomUUID())
                .subtaskTitle(subtaskDto.getSubtaskTitle())
                .status(subtaskDto.isStatus())
                .task(null)
                .build();
    }

}

package com.todolist.model.service.mapping;

import com.todolist.model.repository.dto.SubtaskDTO;
import com.todolist.model.repository.entity.SubtaskEntity;


public class SubtaskMapper {

    public SubtaskDTO fromEntity(SubtaskEntity subtaskEntity) {
        return SubtaskDTO.builder()
                .subtaskTitle(subtaskEntity.getSubtaskTitle())
                .status(subtaskEntity.isStatus())
                .build();
    }

    public SubtaskEntity toEntity(SubtaskDTO subtaskDTO) {
        return SubtaskEntity.builder()
                .subtaskTitle(subtaskDTO.getSubtaskTitle())
                .status(subtaskDTO.isStatus())
                .task(null)
                .build();
    }

}

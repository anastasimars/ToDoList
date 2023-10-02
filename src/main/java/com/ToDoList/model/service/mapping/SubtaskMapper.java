package com.ToDoList.model.service.mapping;

import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.entity.SubtaskEntity;


public class SubtaskMapper {

    public SubtaskDTO fromEntity (SubtaskEntity subtaskEntity){
        return SubtaskDTO.builder()
                .id(subtaskEntity.getId())
                .subtaskTitle(subtaskEntity.getSubtaskTitle())
                .status(subtaskEntity.isStatus())
                .build();
    }

    public SubtaskEntity toEntity (SubtaskDTO subtaskDTO){
        return SubtaskEntity.builder()
                .id(subtaskDTO.getId())
                .subtaskTitle(subtaskDTO.getSubtaskTitle())
                .status(subtaskDTO.isStatus())
                .task(null)
                .build();
    }

}

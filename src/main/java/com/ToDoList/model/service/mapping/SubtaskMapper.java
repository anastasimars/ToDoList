package com.ToDoList.model.service.mapping;

import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.entity.Subtask;
import org.springframework.stereotype.Component;

@Component
public class SubtaskMapper {

    public SubtaskDTO fromEntity (Subtask subtask){
        return SubtaskDTO.builder()
                .id(subtask.getId())
                .subtaskTitle(subtask.getSubtaskTitle())
                .status(subtask.isStatus())
                .task(subtask.getTask())
                .build();
    }

    public Subtask toEntity (SubtaskDTO subtaskDTO){
        return Subtask.builder()
                .id(subtaskDTO.getId())
                .subtaskTitle(subtaskDTO.getSubtaskTitle())
                .status(subtaskDTO.isStatus())
                .task(subtaskDTO.getTask())
                .build();
    }
}

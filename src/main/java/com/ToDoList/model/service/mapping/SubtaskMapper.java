package com.ToDoList.model.service.mapping;

import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.entity.Subtask;
import com.ToDoList.model.repository.entity.Task;

public class SubtaskMapper {

    public SubtaskDTO fromEntity (Subtask subtask){
        return SubtaskDTO.builder()
                .id(subtask.getId())
                .subtaskTitle(subtask.getSubtaskTitle())
                .deadline(subtask.getDeadline())
                .status(subtask.isStatus())
                .build();
    }

    public Subtask toEntity (SubtaskDTO subtaskDTO, Task task){
        return Subtask.builder()
                .id(subtaskDTO.getId())
                .subtaskTitle(subtaskDTO.getSubtaskTitle())
                .deadline(subtaskDTO.getDeadline())
                .status(subtaskDTO.isStatus())
                .task(task)
                .build();
    }

}

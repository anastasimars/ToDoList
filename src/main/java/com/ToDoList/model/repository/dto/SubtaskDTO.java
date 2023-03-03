package com.ToDoList.model.repository.dto;

import com.ToDoList.model.repository.entity.Subtask;
import com.ToDoList.model.repository.entity.Task;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubtaskDTO {
    private Long id;
    private String subtaskTitle;
    private Task task;

    public static SubtaskDTO fromEntity (Subtask subtask){
        return new SubtaskDTO(subtask.getId(), subtask.getSubtaskTitle(), subtask.getTask());
    }


}

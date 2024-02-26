package com.ToDoList.model.repository.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private String taskTitle;
    private LocalDate deadline;
    private boolean status;
    private List<SubtaskDTO> subtasks = new ArrayList<>();
}

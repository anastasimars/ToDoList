package com.ToDoList.model.repository.dto;

import com.ToDoList.model.repository.entity.Subtask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private Long id;
    private String taskTitle;
    private LocalDate deadline;
    private List<Subtask> subtasks;
    private boolean status;
}

package com.todolist.model.repository.dto;

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
public class TaskDtoRequest {
    private String taskTitle;
    private LocalDate deadline;
    private List<SubtaskDtoRequest> subtasks;
}

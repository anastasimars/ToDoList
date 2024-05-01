package com.todolist.model.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDtoResponse {
    private UUID techId;
    private String taskTitle;
    private LocalDate deadline;
    private boolean status;
    private List<SubtaskDtoResponse> subtasks;
}

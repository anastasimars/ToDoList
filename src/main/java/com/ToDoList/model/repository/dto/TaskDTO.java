package com.ToDoList.model.repository.dto;

import com.ToDoList.model.repository.entity.Subtask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private Long id;
    private String taskTitle;
    private List<Subtask> subtasks;
}

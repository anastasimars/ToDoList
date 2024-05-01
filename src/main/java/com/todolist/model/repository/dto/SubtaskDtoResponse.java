package com.todolist.model.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubtaskDtoResponse {
    private UUID techId;
    private String subtaskTitle;
    private boolean status;
}

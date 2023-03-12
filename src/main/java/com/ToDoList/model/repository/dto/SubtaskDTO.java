package com.ToDoList.model.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubtaskDTO {
    private Long id;
    private String subtaskTitle;
    private boolean status;
    private Integer deadline;

}

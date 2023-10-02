package com.ToDoList.model.service.mapping;

import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.repository.entity.TaskEntity;

public class TaskMapper {

    public TaskDTO fromEntity (TaskEntity taskEntity){

      return TaskDTO.builder()
              .id(taskEntity.getId())
              .taskTitle(taskEntity.getTaskTitle())
              .deadline(taskEntity.getDeadline())
              .status(taskEntity.isStatus())
              .build();
    }

    public TaskEntity toEntity (TaskDTO taskDTO) {
        return TaskEntity.builder()
                .id(taskDTO.getId())
                .taskTitle(taskDTO.getTaskTitle())
                .deadline(taskDTO.getDeadline())
                .status(taskDTO.isStatus())
                .build();
    }


}

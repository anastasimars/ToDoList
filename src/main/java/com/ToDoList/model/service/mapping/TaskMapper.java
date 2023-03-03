package com.ToDoList.model.service.mapping;

import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.repository.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDTO fromEntity (Task task){
      return TaskDTO.builder()
              .id(task.getId())
              .taskTitle(task.getTaskTitle())
              .subtasks(task.getSubtasks())
              .build();
    }

    public Task toEntity (TaskDTO taskDTO) {
        return Task.builder()
                .id(taskDTO.getId())
                .taskTitle(taskDTO.getTaskTitle())
                .subtasks(taskDTO.getSubtasks())
                .build();
    }


}

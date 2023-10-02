package com.ToDoList.model.service.logic;


import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.repository.entity.SubtaskEntity;
import com.ToDoList.model.repository.entity.TaskEntity;

import java.util.List;

public interface ToDoAPI {

    List<TaskDTO> getAllTasks();

    TaskEntity getTask(Long id);

    void addTask(TaskDTO taskDTO);

    void updateTask(TaskDTO taskDTO, Long id);

    void deleteTask(Long id);

    TaskEntity markTaskAsCompleted(Long taskId);

    List<SubtaskDTO> getAllSubtasksByTaskId(Long id);

    void addSubtask(Long id, SubtaskDTO subtaskDTO);

    void editSubtask(Long id, SubtaskDTO subtaskDTO);

    void deleteSubtask(Long subtaskId);

    SubtaskEntity markSubtaskAsCompleted(Long subtaskId);
}

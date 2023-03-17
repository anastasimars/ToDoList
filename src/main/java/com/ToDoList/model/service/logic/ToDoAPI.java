package com.ToDoList.model.service.logic;


import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.repository.entity.Subtask;
import com.ToDoList.model.repository.entity.Task;

import java.util.List;

public interface ToDoAPI {

    List<TaskDTO> getAllTasks();

    void addTask(TaskDTO taskDTO);

    void updateTask(TaskDTO taskDTO, Long id);

    void deleteTask(Long id);

    Task markTaskAsCompleted(Long taskId);

    List<SubtaskDTO> getAllSubtasksByTaskId(Long id);

    void addSubtask(Long id, SubtaskDTO subtaskDTO);

    void editSubtask(Long id, SubtaskDTO subtaskDTO);

    void deleteSubtask(Long subtaskId);

    Subtask markSubtaskAsCompleted(Long subtaskId);
}

package com.todolist.model.service.logic;


import com.todolist.model.repository.dto.SubtaskDTO;
import com.todolist.model.repository.dto.TaskDTO;
import com.todolist.model.repository.entity.SubtaskEntity;

import java.util.List;

public interface ToDoAPI {

    List<TaskDTO> getAllTasks();

    TaskDTO findTaskWithSubtasks(Long taskId);

    void addTask(TaskDTO taskDTO);

    void updateTask(TaskDTO taskDTO, Long id);

    void deleteTask(Long id);

    TaskDTO markTaskAsCompleted(Long taskId);

    List<SubtaskDTO> getAllSubtasksByTaskId(Long id);

    void addSubtask(Long id, SubtaskDTO subtaskDTO);

    void editSubtask(Long id, SubtaskDTO subtaskDTO);

    void deleteSubtask(Long subtaskId);

    SubtaskEntity markSubtaskAsCompleted(Long subtaskId);
}

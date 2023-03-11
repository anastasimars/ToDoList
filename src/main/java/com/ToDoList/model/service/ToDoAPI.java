package com.ToDoList.model.service;


import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.dto.TaskDTO;

import java.util.List;

public interface ToDoAPI {

    List<TaskDTO> getAllTasks();

    void addTask(TaskDTO taskDTO);

    void updateTask(TaskDTO taskDTO, Long id);

    void deleteTask(Long id);

    TaskDTO getAllWithSubtasks();

    void addSubtask(Long id, TaskDTO taskDTO);

    SubtaskDTO getSubtaskById(SubtaskDTO subtaskDTO);

    void editSubtaskById(Long id);

    void deleteSubtaskById(Long id);
}

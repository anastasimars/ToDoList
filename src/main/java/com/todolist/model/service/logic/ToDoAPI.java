package com.todolist.model.service.logic;


import com.todolist.model.repository.dto.SubtaskDto;
import com.todolist.model.repository.dto.TaskDto;
import com.todolist.model.repository.entity.SubtaskEntity;

import java.util.List;
import java.util.UUID;

public interface ToDoAPI {

    List<TaskDto> getAllTasks();

    TaskDto findTaskWithSubtasks(UUID taskId);

    void addTask(TaskDto taskDto);

    void editTask(TaskDto taskDto, UUID taskId);

    void deleteTask(UUID taskId);

    TaskDto markTaskAsCompleted(UUID taskId);

    void addSubtask(UUID taskId, SubtaskDto subtaskDto);

    void editSubtask(UUID subtaskId, SubtaskDto subtaskDto);

    void deleteSubtask(UUID taskId, UUID subtaskId);

    SubtaskEntity markSubtaskAsCompleted(UUID subtaskId);
}

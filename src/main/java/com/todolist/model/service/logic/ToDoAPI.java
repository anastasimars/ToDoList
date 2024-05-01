package com.todolist.model.service.logic;


import com.todolist.model.repository.dto.SubtaskDtoRequest;
import com.todolist.model.repository.dto.SubtaskDtoResponse;
import com.todolist.model.repository.dto.TaskDtoRequest;
import com.todolist.model.repository.dto.TaskDtoResponse;
import com.todolist.model.repository.entity.SubtaskEntity;

import java.util.List;
import java.util.UUID;

public interface ToDoAPI {

    List<TaskDtoResponse> getAllTasks();

    TaskDtoResponse findTaskWithSubtasks(UUID taskId);

    void addTask(TaskDtoRequest taskDtoRequest);

    void editTask(TaskDtoRequest taskDtoRequest, UUID taskId);

    void deleteTask(UUID taskId);

    TaskDtoResponse markTaskAsCompleted(UUID taskId);

    void addSubtask(UUID taskId, SubtaskDtoRequest subtaskDtoRequest);

    void editSubtask(UUID subtaskId, SubtaskDtoRequest subtaskDtoRequest, boolean status);

    void deleteSubtask(UUID taskId, UUID subtaskId);

    SubtaskEntity markSubtaskAsCompleted(UUID subtaskId, boolean status);
}

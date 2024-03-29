package com.ToDoList.model.service.logic;

import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.repository.entity.SubtaskEntity;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class ToDoFacade implements ToDoAPI {
    private final TaskOperations taskOperations;
    private final SubtaskOperations subtaskOperations;

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskOperations.getAll();
    }

    @Override
    public TaskDTO findTaskWithSubtasks(Long taskId) {
        return taskOperations.getTaskWithSubtasks(taskId);
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        taskOperations.addTask(taskDTO);
    }

    @Override
    public void updateTask(TaskDTO taskDTO, Long id) {
        taskOperations.updateTask(taskDTO, id);
    }

    @Override
    public void deleteTask(Long id) {
        taskOperations.deleteTask(id);
    }

    @Override
    public TaskDTO markTaskAsCompleted(Long taskId) {
        return taskOperations.markTaskAsCompleted(taskId);
    }

    @Override
    public List<SubtaskDTO> getAllSubtasksByTaskId(Long id) {
        return subtaskOperations.getAllSubtasksByTaskId(id);
    }

    @Override
    public void addSubtask(Long id, SubtaskDTO subtaskDTO) {
        subtaskOperations.addSubtask(id, subtaskDTO);
    }

    @Override
    public void editSubtask(Long id, SubtaskDTO subtaskDTO) {
        subtaskOperations.editSubtask(id, subtaskDTO);
    }

    @Override
    public void deleteSubtask(Long id) {
        subtaskOperations.deleteSubtask(id);
    }

    @Override
    public SubtaskEntity markSubtaskAsCompleted(Long subtaskId) {
        return subtaskOperations.markSubtaskAsCompleted(subtaskId);
    }
}

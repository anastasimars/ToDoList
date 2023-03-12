package com.ToDoList.model.service.logic;

import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.dto.TaskDTO;
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
    public List<SubtaskDTO> getAllSubtasksByTaskId(Long id) {
        return subtaskOperations.getAllSubtasksByTaskId(id);
    }

    @Override
    public void addSubtask(Long id, SubtaskDTO subtaskDTO) {
        subtaskOperations.addSubtask(id, subtaskDTO);
    }

    @Override
    public void editSubtask(Long id) {

    }

    @Override
    public void deleteSubtask(Long id) {

    }
}

package com.ToDoList.model.service.logic;

import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.dto.TaskDTO;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
class ToDoFacade implements ToDoAPI{
    private final TaskOperations taskOperations;
    @Override
    public List<TaskDTO> getAllTasks() {
        return taskOperations.getAll();
    }

    @Override
    public void addTask(TaskDTO taskDTO) {

    }

    @Override
    public void updateTask(TaskDTO taskDTO, Long id) {

    }

    @Override
    public void deleteTask(Long id) {

    }

    @Override
    public List<SubtaskDTO> getAllSubtasksByTaskId(Long id) {
        return null;
    }

    @Override
    public void addSubtask(Long id, SubtaskDTO subtaskDTO) {

    }


    @Override
    public SubtaskDTO getSubtaskById(Long id) {
        return null;
    }

    @Override
    public void editSubtaskById(Long id) {

    }

    @Override
    public void deleteSubtaskById(Long id) {

    }
}

package com.ToDoList.model.service;

import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.dto.TaskDTO;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
class ToDoFacade implements ToDoAPI{
    private final ReadTaskOperation readTaskOperation;
    @Override
    public List<TaskDTO> getAllTasks() {
        return readTaskOperation.getAll();
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
    public TaskDTO getAllWithSubtasks() {
        return null;
    }

    @Override
    public void addSubtask(Long id, TaskDTO taskDTO) {

    }

    @Override
    public SubtaskDTO getSubtaskById(SubtaskDTO subtaskDTO) {
        return null;
    }

    @Override
    public void editSubtaskById(Long id) {

    }

    @Override
    public void deleteSubtaskById(Long id) {

    }
}

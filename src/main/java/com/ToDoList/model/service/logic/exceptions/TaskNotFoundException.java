package com.ToDoList.model.service.logic.exceptions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(Long id) {
        super("No found with the specified ID:" + id);
    }
}

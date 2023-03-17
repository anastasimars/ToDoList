package com.ToDoList.model.service.logic;

public class NotFoundException extends RuntimeException{
    public NotFoundException(Long id) {
        super("No found with the specified ID:" + id);
    }
}

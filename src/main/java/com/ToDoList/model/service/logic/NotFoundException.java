package com.ToDoList.model.service.logic;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("No found with the specified ID");
    }
}

package com.ToDoList.model.service.logic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TaskNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String taskNotFoundHandler(TaskNotFoundException taskNotFoundException) {
        return taskNotFoundException.getMessage();
    }
}

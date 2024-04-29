package com.todolist.model.service.logic.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("No found with the specified ID:" + id);
    }
}

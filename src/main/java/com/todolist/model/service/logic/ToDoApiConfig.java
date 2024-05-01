package com.todolist.model.service.logic;

import com.todolist.model.repository.SubtaskRepository;
import com.todolist.model.repository.TaskRepository;
import com.todolist.model.service.mapping.ToDoMappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ToDoApiConfig {

    @Bean
    public ToDoAPI toDoAPI(TaskRepository taskRepository,
                           SubtaskRepository subtaskRepository,
                           ToDoMappers mappers) {

        return new ToDoService(taskRepository, subtaskRepository, mappers);
    }

    @Bean
    public ToDoMappers toDoMappers() {
        return new ToDoMappers();
    }
}

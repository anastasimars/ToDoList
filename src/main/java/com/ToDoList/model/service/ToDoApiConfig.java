package com.ToDoList.model.service;

import com.ToDoList.model.repository.SubtaskRepository;
import com.ToDoList.model.repository.TaskRepository;
import com.ToDoList.model.service.mapping.SubtaskMapper;
import com.ToDoList.model.service.mapping.TaskMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ToDoApiConfig {

    @Bean
    public ToDoAPI toDoAPI(TaskRepository taskRepository,
                           SubtaskRepository subtaskRepository,
                           TaskMapper taskMapper,
                           SubtaskMapper subtaskMapper) {
        ReadTaskOperation readTaskOperation =
                new ReadTaskOperation(taskRepository, subtaskRepository, taskMapper, subtaskMapper);
        return new ToDoFacade(readTaskOperation);
    }
}

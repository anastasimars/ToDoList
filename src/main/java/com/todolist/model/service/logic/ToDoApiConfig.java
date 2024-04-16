package com.todolist.model.service.logic;

import com.todolist.model.repository.SubtaskRepository;
import com.todolist.model.repository.TaskRepository;
import com.todolist.model.service.mapping.SubtaskMapper;
import com.todolist.model.service.mapping.TaskMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ToDoApiConfig {

    @Bean
    public ToDoAPI toDoAPI(TaskRepository taskRepository,
                           SubtaskRepository subtaskRepository,
                           TaskMapper taskMapper,
                           SubtaskMapper subtaskMapper) {

        final TaskOperations taskOperations =
                new TaskOperations(taskRepository, taskMapper);

        final SubtaskOperations subtaskOperations =
                new SubtaskOperations(subtaskRepository, taskRepository, subtaskMapper);

        return new ToDoFacade(taskOperations, subtaskOperations);
    }

    @Bean
    public SubtaskMapper subtaskMapper() {
        return new SubtaskMapper();
    }

    @Bean
    public TaskMapper taskMapper() {
        return new TaskMapper();
    }
}

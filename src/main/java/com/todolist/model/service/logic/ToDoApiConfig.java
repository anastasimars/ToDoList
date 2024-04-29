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

        return new ToDoService(taskRepository, subtaskRepository, taskMapper, subtaskMapper);
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

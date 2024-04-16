package com.todolist.model.service.logic

import com.todolist.model.repository.SubtaskRepository
import com.todolist.model.repository.TaskRepository
import com.todolist.model.service.mapping.SubtaskMapper
import com.todolist.model.service.mapping.TaskMapper
import spock.lang.Specification

abstract class TodoAPISpecConfiguration extends Specification{

    protected final TaskRepository taskRepository = Mock(TaskRepository)
    protected final SubtaskRepository subtaskRepository = Mock(SubtaskRepository)
    private final TaskMapper taskMapper = new TaskMapper()
    private final SubtaskMapper subtaskMapper = new SubtaskMapper()

    ToDoAPI toDoAPI(){
        return new ToDoApiConfig().toDoAPI(taskRepository, subtaskRepository, taskMapper, subtaskMapper)
    }

}

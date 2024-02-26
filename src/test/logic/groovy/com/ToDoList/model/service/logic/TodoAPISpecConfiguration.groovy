package com.ToDoList.model.service.logic

import com.ToDoList.model.repository.SubtaskRepository
import com.ToDoList.model.repository.TaskRepository
import com.ToDoList.model.service.mapping.SubtaskMapper
import com.ToDoList.model.service.mapping.TaskMapper
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

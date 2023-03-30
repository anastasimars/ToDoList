package com.ToDoList.model.service.logic

import com.ToDoList.model.repository.dto.TaskDTO
import com.ToDoList.model.repository.entity.Task


class TodoAPISpec extends TodoAPISpecConfiguration {

    private final ToDoAPI toDoAPI = super.toDoAPI()

    def "sadas"() {
        given:
        final String givenTitle = "test-title"
        taskRepository.findAll() >> List.of(new Task(taskTitle: givenTitle))

        when:
        def actualResponse = toDoAPI.getAllTasks()

        then:
        actualResponse.get(0).getTaskTitle()  == givenTitle
    }
}

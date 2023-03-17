package com.ToDoList.model.service.logic

import com.ToDoList.model.repository.TaskRepository
import com.ToDoList.model.repository.dto.TaskDTO
import com.ToDoList.model.repository.entity.Task
import com.ToDoList.model.service.mapping.TaskMapper
import spock.lang.Specification
import spock.lang.Subject

class TaskOperationsSpec extends Specification {
    private final TaskRepository taskRepository = Mock()
    private final TaskMapper taskMapper = Mock()
    @Subject
    private final TaskOperations taskOperations = new TaskOperations(taskRepository, taskMapper)

    def "should correct find all tasks"() {
        given: "tasks for mock"
        final Task givenSubtask1 = new Task()
        final Task givenSubtask2 = new Task()

        and: "prepare mock response from repository"
        taskRepository.findAll() >> List.of(givenSubtask1, givenSubtask2)

        when: "invoke method getAll in TaskOperations"
        final List<TaskDTO> actualListOfTasksDTO = taskOperations.getAll()

        then: "size should be correct"
        actualListOfTasksDTO.size() == 2

    }


}

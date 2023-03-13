package com.ToDoList.model.service.mapping


import com.ToDoList.model.repository.dto.TaskDTO
import com.ToDoList.model.repository.entity.Task
import spock.lang.Specification

class TaskMapperSpec extends Specification {
TaskMapper taskMapper = new TaskMapper();
    def "should properly map entity to dto"() {
        given:"test data for Task"
        final givenTaskTitle = "Test task"

        and: "prepared Task"
        final Task task = Task.builder()
                .taskTitle(givenTaskTitle)
                .build()

        when: "perform mapping"
        final TaskDTO taskDTO = taskMapper.fromEntity(task)

        then: "assertion should be correct"
        taskDTO.taskTitle == givenTaskTitle
    }

    def "should properly map dto to entity"() {
        given:"test data for TaskDTO"
        final givenTaskTitle = "Test task"

        and: "prepared TaskDTO"
        final TaskDTO taskDTO = TaskDTO.builder()
                .taskTitle(givenTaskTitle)
                .build()

        when: "perform mapping"
        final Task task = taskMapper.toEntity(taskDTO)

        then: "assertion should be correct"
        task.taskTitle == givenTaskTitle
    }
}

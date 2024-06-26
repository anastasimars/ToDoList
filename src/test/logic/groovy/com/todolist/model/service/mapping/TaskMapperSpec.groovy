package com.todolist.model.service.mapping


import com.todolist.model.repository.dto.TaskDto
import com.todolist.model.repository.entity.TaskEntity
import spock.lang.Specification

class TaskMapperSpec extends Specification {
    private final TaskMapper taskMapper = new TaskMapper()

    def "should properly map entity to dto"() {
        given: "test data for Task"
        final givenTaskTitle = "Test task"

        and: "prepared Task"
        final TaskEntity task = TaskEntity.builder()
                .taskTitle(givenTaskTitle)
                .build()

        when: "perform mapping"
        final TaskDto actualTaskDTO = taskMapper.fromEntity(task)

        then: "assertion should be correct"
        actualTaskDTO.taskTitle == givenTaskTitle
    }

    def "should properly map dto to entity"() {
        given: "test data for TaskDTO"
        final givenTaskTitle = "Test task"

        and: "prepared TaskDTO"
        final TaskDto taskDTO = TaskDto.builder()
                .taskTitle(givenTaskTitle)
                .build()

        when: "perform mapping"
        final TaskEntity actualTask = taskMapper.toEntity(taskDTO)

        then: "assertion should be correct"
        actualTask.taskTitle == givenTaskTitle
    }
}

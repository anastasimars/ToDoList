package com.ToDoList.model.service.mapping

import com.ToDoList.model.repository.dto.SubtaskDTO
import com.ToDoList.model.repository.entity.Subtask
import com.ToDoList.model.repository.entity.Task
import spock.lang.Specification
import spock.lang.Subject

class SubtaskMapperSpec extends Specification {

    @Subject
    private final SubtaskMapper subtaskMapper = new SubtaskMapper();

    def "should properly map entity to dto"() {
        given: "test data for Subtask"
        final givenSubtaskTitle = "test-title"
        final givenStatus = false

        and: "prepared subtask"
        final givenSubtask = Subtask.builder()
                .subtaskTitle(givenSubtaskTitle)
                .status(givenStatus)
                .build()

        when: "perform mapping"
        final actualSubtaskDTO = subtaskMapper.fromEntity(givenSubtask)

        then: "assertion should be correct"
        actualSubtaskDTO.subtaskTitle == givenSubtaskTitle
        actualSubtaskDTO.status == givenStatus
    }

    def "should properly map dto to entity"() {
        given: "test data for SubtaskDTO"
        final givenSubtaskTitle = "test-title"
        final givenStatus = false
        final givenTaskTitle = "Test task"
        final Task task = Task.builder()
                .taskTitle(givenTaskTitle)
                .build()

        and: "prepared subtaskDTO"
        final SubtaskDTO subtaskDTO = SubtaskDTO.builder()
                .subtaskTitle(givenSubtaskTitle)
                .status(givenStatus)
                .build()

        when: "perform mapping"
        final Subtask actualSubtask = subtaskMapper.toEntity(subtaskDTO)

        then: "assertion should be correct"
        actualSubtask.subtaskTitle == givenSubtaskTitle
        actualSubtask.status == givenStatus

    }
}

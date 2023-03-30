package com.ToDoList.model.service.logic

import com.ToDoList.model.repository.SubtaskRepository
import com.ToDoList.model.repository.TaskRepository
import com.ToDoList.model.repository.dto.SubtaskDTO
import com.ToDoList.model.repository.entity.Subtask
import com.ToDoList.model.repository.entity.Task
import com.ToDoList.model.service.mapping.SubtaskMapper
import spock.lang.Specification
import spock.lang.Subject

class SubtaskOperationsSpec extends Specification {
    private final SubtaskRepository subtaskRepository = Mock()
    private final TaskRepository taskRepository = Mock()
    private final SubtaskMapper subtaskMapper = Mock()

    @Subject
    private final SubtaskOperations subtaskOperations = new SubtaskOperations(subtaskRepository, taskRepository, subtaskMapper)

    def "should correct find all tasks by task id"() {
        given: "task and subtasks for mock"
        final Task givenTask = new Task()
        final Subtask givenSubtask1 = new Subtask()
        final Subtask givenSubtask2 = new Subtask()
        final Integer givenSize = 2

        and: "prepare mock response from repository"
        subtaskRepository.findAll() >> List.of(givenSubtask1, givenSubtask2)

        when: "invoke method getAll in SubtaskOperations"
        List<SubtaskDTO> actualListOfSubtaskDTO = subtaskOperations.getAllSubtasksByTaskId(givenTask.getId())

        then: "size should be correct"
        actualListOfSubtaskDTO.size() == givenSize

    }
}

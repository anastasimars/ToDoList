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
    private final SubtaskMapper subtaskMapper = new SubtaskMapper()

    @Subject
    private final SubtaskOperations subtaskOperations = new SubtaskOperations(subtaskRepository, taskRepository, subtaskMapper)

    def "should correct find all tasks by task id"() {
        given: "task and subtasks for mock"
        final Task givenTask = new Task()
        final Subtask givenSubtask1 = new Subtask()
        final Subtask givenSubtask2 = new Subtask()
        final Integer givenSize = 2

        and: "prepare mock response from repository"
        subtaskRepository.findAllByTaskId(givenTask.getId()) >> List.of(givenSubtask1, givenSubtask2)

        when: "invoke method getAll in SubtaskOperations"
        List<SubtaskDTO> actualListOfSubtaskDTO = subtaskOperations.getAllSubtasksByTaskId(givenTask.getId())

        then: "size should be correct"
        actualListOfSubtaskDTO.size() == givenSize
    }

    def "check if added subtask to task"() {
        given: "prepared task and subtask entity"
        final Long givenId = 1L
        final Task givenTask = new Task(id: givenId)
        final SubtaskDTO givenSubtaskDTO = new SubtaskDTO()

        and: "prepare mock response from repository"
        taskRepository.findById(givenId) >> Optional.of(givenTask)
        subtaskRepository.save(_ as Subtask) >> null

        when: "invoke method"
        subtaskOperations.addSubtask(givenId, givenSubtaskDTO)

        then:
        1 * subtaskRepository.save(_ as Subtask)
    }

    def "should be thrown not found exception if repository return empty optional"() {
        given: "prepared task and subtask entity"
        final Long givenId = 1L
        final SubtaskDTO givenSubtaskDTO = new SubtaskDTO()

        and: "prepare mock response from repository"
        taskRepository.findById(givenId) >> Optional.empty()

        when: "invoke method"
        subtaskOperations.addSubtask(givenId, givenSubtaskDTO)

        then:
        thrown(NotFoundException)
    }


    def "EditSubtask"() {
    }

    def "DeleteSubtask"() {
    }

    def "MarkSubtaskAsCompleted"() {
    }
}

package com.ToDoList.model.service.logic

import com.ToDoList.model.repository.dto.SubtaskDTO
import com.ToDoList.model.repository.dto.TaskDTO
import com.ToDoList.model.repository.entity.Subtask
import com.ToDoList.model.repository.entity.Task
import com.ToDoList.model.service.logic.faults.NotFoundException
import org.checkerframework.checker.nullness.Opt


class TodoAPISpec extends TodoAPISpecConfiguration {

    private final ToDoAPI toDoAPI = super.toDoAPI()

    def "should correct find all tasks"() {
        given: "tasks for mock"
        final Task givenTask1 = new Task()
        final Task givenTask2 = new Task()

        and: "prepare mock response from repository"
        taskRepository.findAll() >> List.of(givenTask1, givenTask2)

        when: "invoke method getAll in TaskOperations"
        final List<TaskDTO> actualListOfTasksDTO = toDoAPI.getAllTasks()

        then: "size should be correct"
        actualListOfTasksDTO.size() == 2
    }

    def "check if added task"() {
        given: "prepared task and subtaskDTO"
        final Long givenId = 1L
        final TaskDTO givenTaskDTO = new TaskDTO(id:givenId)

        and: "prepare mock response from repository"
        taskRepository.findById(givenId) >> Optional.of(givenTaskDTO)

        when: "invoke method"
        toDoAPI.addTask(givenTaskDTO)

        then: "method save should be invoked only one time"
        1 * taskRepository.save(_ as Task)
    }

    def "happy path - should properly edit task"() {
        given: "prepared TaskDTO"
        final Long givenId = 1L
        final String oldTitle = "Old test title"
        final String newTitle = "New test title"
        final TaskDTO givenTaskDTO = TaskDTO.builder().id(givenId).taskTitle(newTitle).build()
        final Task givenOldTaskEntity = Task.builder().id(givenId).taskTitle(oldTitle).build()
        final Task givenNewTaskEntity = Task.builder().id(givenId).taskTitle(newTitle).build()

        when: "invoke method"
        toDoAPI.updateTask(givenTaskDTO, givenId)

        then: "check if noExceptionThrown"
        noExceptionThrown()

        and: "check mock invocation"
        1 * taskRepository.findById(givenId) >> Optional.of(givenOldTaskEntity)
        1 * taskRepository.save(_ as Task) >> givenNewTaskEntity
    }

    def "check if deleted task"() {
    }

    def "check if task completed"() {
    }

    def "should correct find all subtasks by task id"() {
        given: "task and subtasks for mock"
        final Task givenTask = new Task()
        final Subtask givenSubtask1 = new Subtask()
        final Subtask givenSubtask2 = new Subtask()
        final Integer givenSize = 2

        and: "prepare mock response from repository"
        subtaskRepository.findAllByTaskId(givenTask.getId()) >> List.of(givenSubtask1, givenSubtask2)

        when: "invoke method"
        List<SubtaskDTO> actualListOfSubtaskDTO = toDoAPI.getAllSubtasksByTaskId(givenTask.getId())

        then: "size should be correct"
        actualListOfSubtaskDTO.size() == givenSize
    }

    def "check if added subtask to task"() {
        given: "prepared task and subtaskDTO"
        final Long givenId = 1L
        final Task givenTask = new Task(id: givenId)
        final SubtaskDTO givenSubtaskDTO = new SubtaskDTO()

        and: "prepare mock response from repository"
        taskRepository.findById(givenId) >> Optional.of(givenTask)
        subtaskRepository.save(_ as Subtask) >> null

        when: "invoke method"
        toDoAPI.addSubtask(givenId, givenSubtaskDTO)

        then: "method save should be invoked only one time"
        1 * subtaskRepository.save(_ as Subtask)
    }

    def "unhappy path - should be thrown not found exception if repository return empty optional"() {
        given: "prepared task and subtaskDTO"
        final Long givenId = 1L
        final SubtaskDTO givenSubtaskDTO = new SubtaskDTO()

        and: "prepare mock response from repository"
        taskRepository.findById(givenId) >> Optional.empty()

        when: "invoke method"
        toDoAPI.addSubtask(givenId, givenSubtaskDTO)


        then: "NotFoundException should be thrown"
        thrown(NotFoundException)
    }


    def "happy path - should properly edit subtask"() {
        given: "prepared SubtaskDTO"
        final Long givenId = 1L
        final String givenOldTitle = "Old test title"
        final String givenNewTitle = "New test title"
        final SubtaskDTO givenSubtaskDTO = SubtaskDTO.builder().id(givenId).subtaskTitle(givenNewTitle).build()
        final Subtask givenOldSubtaskEntity = Subtask.builder().id(givenId).subtaskTitle(givenOldTitle).build()
        final Subtask givenNewSubtaskEntity = Subtask.builder().id(givenId).subtaskTitle(givenNewTitle).build()

        when: "invoke method"
        toDoAPI.editSubtask(givenId, givenSubtaskDTO)

        then: "check if noExceptionThrown"
        noExceptionThrown()

        and: "check mock invocation"
        1 * subtaskRepository.findById(givenId) >> Optional.of(givenOldSubtaskEntity)
        1 * subtaskRepository.save(_ as Subtask) >> givenNewSubtaskEntity

    }

    def "check if deleted subtask"() {
    }

    def "check if subtask completed"() {
    }




}

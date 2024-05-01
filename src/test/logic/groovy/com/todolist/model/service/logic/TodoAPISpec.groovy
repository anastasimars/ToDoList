package com.todolist.model.service.logic

import com.todolist.model.repository.dto.SubtaskDtoResponse
import com.todolist.model.repository.dto.TaskDtoResponse
import com.todolist.model.repository.entity.SubtaskEntity
import com.todolist.model.repository.entity.TaskEntity
import com.todolist.model.service.logic.exceptions.NotFoundException



class TodoAPISpec extends TodoAPISpecConfiguration {

    private final ToDoAPI toDoAPI = super.toDoAPI()

    def "should correct find all tasks"() {
        given: "prepared TaskEntities for mock"
        final TaskEntity givenTask1 = new TaskEntity()
        final TaskEntity givenTask2 = new TaskEntity()

        and: "prepare mock response from repository"
        taskRepository.findAll() >> List.of(givenTask1, givenTask2)

        when: "invoke method getAll in TaskOperations"
        final List<TaskDtoResponse> actualListOfTasksDTO = toDoAPI.getAllTasks()

        then: "size should be correct"
        actualListOfTasksDTO.size() == 2
    }

    def "check if added task"() {
        given: "prepared SubtaskDTO"
        final Long givenId = 1L
        final TaskDtoResponse givenTaskDTO = new TaskDtoResponse(id:givenId)

        and: "prepare mock response from repository"
        taskRepository.findById(givenId) >> Optional.of(givenTaskDTO)

        when: "invoke method"
        toDoAPI.addTask(givenTaskDTO)

        then: "method save should be invoked only one time"
        1 * taskRepository.save(_ as TaskEntity)
    }

    def "happy path - should properly edit task"() {
        given: "prepared TaskDTO"
        final Long givenId = 1L
        final String oldTitle = "Old test title"
        final String newTitle = "New test title"
        final TaskDtoResponse givenTaskDTO = TaskDtoResponse.builder().id(givenId).taskTitle(newTitle).build()
        final TaskEntity givenOldTaskEntity = TaskEntity.builder().id(givenId).taskTitle(oldTitle).build()
        final TaskEntity givenNewTaskEntity = TaskEntity.builder().id(givenId).taskTitle(newTitle).build()

        when: "invoke method"
        toDoAPI.editTask(givenTaskDTO, givenId)

        then: "check if noExceptionThrown"
        noExceptionThrown()

        and: "check mock invocation"
        1 * taskRepository.findById(givenId) >> Optional.of(givenOldTaskEntity)
        1 * taskRepository.save(_ as TaskEntity) >> givenNewTaskEntity
    }

    def "check if deleted task"() {
        final TaskEntity givenTask = new TaskEntity()
    }

    def "check if task completed"() {
    }

    def "should correct find all subtasks by task id"() {
        given: "task and subtasks for mock"
        final TaskEntity givenTask = new TaskEntity()
        final SubtaskEntity givenSubtask1 = new SubtaskEntity()
        final SubtaskEntity givenSubtask2 = new SubtaskEntity()
        final Integer givenSize = 2

        and: "prepare mock response from repository"
        subtaskRepository.findAllByTaskId(givenTask.getId()) >> List.of(givenSubtask1, givenSubtask2)

        when: "invoke method"
        List<SubtaskDtoResponse> actualListOfSubtaskDTO = toDoAPI.getAllSubtasksByTaskId(givenTask.getId())

        then: "size should be correct"
        actualListOfSubtaskDTO.size() == givenSize
    }

    def "check if added subtask to task"() {
        given: "prepared task and subtaskDTO"
        final Long givenId = 1L
        final TaskEntity givenTaskEntity = new TaskEntity(id: givenId)
        final SubtaskEntity givenSubtaskEntity = new SubtaskEntity()
        final SubtaskDtoResponse givenSubtaskDTO = new SubtaskDtoResponse()

        and: "prepare mock response from repository"
        taskRepository.findById(givenId) >> Optional.of(givenTaskEntity)
        taskRepository.save(_ as TaskEntity) >> givenTaskEntity

        when: "invoke method"
        toDoAPI.addSubtask(givenId, givenSubtaskDTO)

        then:"addSubtask is called on the taskEntity"
        1 * givenTask.addSubtask(givenSubtaskEntity)


        "method save should be invoked only one time"
//        1 * subtaskRepository.save(_ as SubtaskEntity)
        1 * taskRepository.save(_ as TaskEntity)
    }

    def "unhappy path - should be thrown not found exception if repository return empty optional"() {
        given: "prepared task and subtaskDTO"
        final Long givenId = 1L
        final SubtaskDtoResponse givenSubtaskDTO = new SubtaskDtoResponse()

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
        final SubtaskDtoResponse givenSubtaskDTO = SubtaskDtoResponse.builder().id(givenId).subtaskTitle(givenNewTitle).build()
        final SubtaskEntity givenOldSubtaskEntity = SubtaskEntity.builder().id(givenId).subtaskTitle(givenOldTitle).build()
        final SubtaskEntity givenNewSubtaskEntity = SubtaskEntity.builder().id(givenId).subtaskTitle(givenNewTitle).build()

        when: "invoke method"
        toDoAPI.editSubtask(givenId, givenSubtaskDTO)

        then: "check if noExceptionThrown"
        noExceptionThrown()

        and: "check mock invocation"
        1 * subtaskRepository.findById(givenId) >> Optional.of(givenOldSubtaskEntity)
        1 * subtaskRepository.save(_ as SubtaskEntity) >> givenNewSubtaskEntity

    }

    def "check if deleted subtask"() {
    }

    def "check if subtask completed"() {
    }




}

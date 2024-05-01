package com.todolist.model.service.logic;

import com.todolist.model.repository.SubtaskRepository;
import com.todolist.model.repository.TaskRepository;
import com.todolist.model.repository.dto.SubtaskDtoRequest;
import com.todolist.model.repository.dto.SubtaskDtoResponse;
import com.todolist.model.repository.dto.TaskDtoRequest;
import com.todolist.model.repository.dto.TaskDtoResponse;
import com.todolist.model.repository.entity.SubtaskEntity;
import com.todolist.model.repository.entity.TaskEntity;
import com.todolist.model.service.logic.exceptions.NotFoundException;
import com.todolist.model.service.mapping.ToDoMappers;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
class ToDoService implements ToDoAPI {
    private final TaskRepository taskRepository;
    private final SubtaskRepository subtaskRepository;
    private final ToDoMappers mappers;

    @Override
    @Transactional(readOnly = true)
    public List<TaskDtoResponse> getAllTasks() {
        return taskRepository.findAllWithSubtasks()
                .stream()
                .map(mappers::fromTaskEntityToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDtoResponse findTaskWithSubtasks(UUID taskId) {
        TaskEntity taskEntity = findTaskByTechId(taskId);
        return mappers.fromTaskEntityToResponse(taskEntity);
    }


    @Override
    @Transactional
    public void addTask(TaskDtoRequest taskDtoRequest) {
        TaskEntity taskEntity = mappers.fromTaskRequestToEntity(taskDtoRequest);
        List<SubtaskEntity> subtaskEntities = mappers.
                toSubtaskEntityList(taskDtoRequest.getSubtasks());
        subtaskEntities.forEach(taskEntity::addSubtask);
        taskRepository.save(taskEntity);
    }

    @Override
    @Transactional
    public void editTask(TaskDtoRequest taskDtoRequest, UUID taskId) {
        TaskEntity task = findTaskByTechId(taskId);
        task.updateTaskTitle(taskDtoRequest.getTaskTitle());
        task.updateDeadline(taskDtoRequest.getDeadline());
        task.updateStatus();
        updateSubtasks(task, taskDtoRequest.getSubtasks());
        taskRepository.save(task);
    }

    private void updateSubtasks(TaskEntity task,
                                List<SubtaskDtoRequest> subtaskDtos){

    }

    @Override
    @Transactional
    public void deleteTask(UUID taskId) {
        TaskEntity taskEntity = findTaskByTechId(taskId);
        taskRepository.delete(taskEntity);
    }

    @Override
    @Transactional
    public TaskDtoResponse markTaskAsCompleted(UUID taskId) {
        TaskEntity taskEntity = findTaskByTechId(taskId);
        taskEntity.updateStatus(); //will 'true' if all subtasks are done
        taskRepository.save(taskEntity);
        return mappers.fromTaskEntityToResponse(taskEntity);
    }

    @Override
    @Transactional
    public void addSubtask(UUID taskId, SubtaskDtoRequest subtaskDtoRequest) {
        TaskEntity taskEntity = findTaskByTechId(taskId);
        SubtaskEntity subtask = mappers
                .fromSubtaskRequestToEntity(subtaskDtoRequest);
        subtask.joinTask(taskEntity);
        subtaskRepository.save(subtask);
    }

    @Override
    @Transactional
    public void editSubtask(UUID subtaskId,
                            SubtaskDtoRequest subtaskDtoRequest,
                            boolean newStatus) {
        SubtaskEntity subtaskEntity = findSubtaskByTechId(subtaskId);
        subtaskEntity.updateSubtaskTitle(subtaskDtoRequest.getSubtaskTitle());
        subtaskEntity.updateSubtaskStatus(newStatus);
        subtaskRepository.save(subtaskEntity);
    }

    @Override
    public void deleteSubtask(UUID taskId, UUID subtaskId) {
        TaskEntity taskEntity = findTaskByTechId(taskId);
        SubtaskEntity subtaskEntity = taskEntity.getSubtasks().stream()
                .filter(s -> s.getTechId().equals(subtaskId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(subtaskId));
        taskEntity.getSubtasks().remove(subtaskEntity);
        taskRepository.save(taskEntity);
    }

    @Override
    public SubtaskEntity markSubtaskAsCompleted(UUID subtaskId, boolean status) {
        SubtaskEntity subtaskEntity = subtaskRepository.findByTechId(subtaskId).orElseThrow(() -> new NotFoundException(subtaskId));
        subtaskEntity.updateSubtaskStatus(status);
        return subtaskRepository.save(subtaskEntity);
    }

    private TaskEntity findTaskByTechId(UUID taskId) {
        return taskRepository
                .findByTechIdWithSubtasks(taskId)
                .orElseThrow(() -> new NotFoundException(taskId));
    }

    private SubtaskEntity findSubtaskByTechId(UUID subtaskId) {
        return subtaskRepository.findByTechId(subtaskId)
                .orElseThrow(() -> new NotFoundException(subtaskId));
    }
}

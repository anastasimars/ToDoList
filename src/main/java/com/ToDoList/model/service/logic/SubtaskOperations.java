package com.ToDoList.model.service.logic;

import com.ToDoList.model.repository.SubtaskRepository;
import com.ToDoList.model.repository.TaskRepository;
import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.entity.SubtaskEntity;
import com.ToDoList.model.repository.entity.TaskEntity;
import com.ToDoList.model.service.logic.exceptions.TaskNotFoundException;
import com.ToDoList.model.service.mapping.SubtaskMapper;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class SubtaskOperations {
    private final SubtaskRepository subtaskRepository;

    private final TaskRepository taskRepository;
    private final SubtaskMapper subtaskMapper;

    List<SubtaskDTO> getAllSubtasksByTaskId(Long id) {
        return subtaskRepository.findAllByTaskId(id).stream()
                .map(subtaskMapper::fromEntity)
                .toList();
    }

    void addSubtask(Long id, SubtaskDTO subtaskDTO) {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        SubtaskEntity subtaskEntity = subtaskMapper.toEntity(subtaskDTO);
        subtaskEntity.setTask(taskEntity);
        subtaskRepository.save(subtaskEntity);
    }


    void editSubtask(Long id, SubtaskDTO subtaskDTO) {
        SubtaskEntity subtaskEntity = subtaskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        subtaskEntity.setSubtaskTitle(subtaskDTO.getSubtaskTitle());
        subtaskRepository.save(subtaskEntity);
    }


    void deleteSubtask(Long id) {
        SubtaskEntity subtaskEntity = subtaskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        subtaskRepository.delete(subtaskEntity);
    }

    SubtaskEntity markSubtaskAsCompleted(Long subtaskId) {
        SubtaskEntity subtaskEntity = subtaskRepository.findById(subtaskId).orElseThrow(() -> new TaskNotFoundException(subtaskId));
        subtaskEntity.setStatus(true);
        return subtaskRepository.save(subtaskEntity);
    }
}

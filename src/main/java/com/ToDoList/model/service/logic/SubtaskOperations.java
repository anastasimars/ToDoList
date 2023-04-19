package com.ToDoList.model.service.logic;

import com.ToDoList.model.repository.SubtaskRepository;
import com.ToDoList.model.repository.TaskRepository;
import com.ToDoList.model.repository.dto.SubtaskDTO;
import com.ToDoList.model.repository.entity.Subtask;
import com.ToDoList.model.repository.entity.Task;
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
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        Subtask subtask = subtaskMapper.toEntity(subtaskDTO);
        subtask.setTask(task);
        subtaskRepository.save(subtask);
    }


    void editSubtask(Long id, SubtaskDTO subtaskDTO) {
        Subtask subtask = subtaskRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        subtask.setSubtaskTitle(subtaskDTO.getSubtaskTitle());
        subtask.setDeadline(subtaskDTO.getDeadline());
    }


    void deleteSubtask(Long id) {
        Subtask subtask = subtaskRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        subtaskRepository.delete(subtask);
    }

    Subtask markSubtaskAsCompleted(Long subtaskId) {
        Subtask subtask = subtaskRepository.findById(subtaskId).orElseThrow(() -> new NotFoundException(subtaskId));
        subtask.setStatus(true);
        return subtaskRepository.save(subtask);
    }
}

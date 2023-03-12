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
public class SubtaskOperations {
    private final SubtaskRepository subtaskRepository;

    private final TaskRepository taskRepository;
    private final SubtaskMapper subtaskMapper;
    public List<SubtaskDTO> getAllSubtasksByTaskId(Long id) {
        return subtaskRepository.findAllByTaskId(id).stream()
                .map(subtaskMapper::fromEntity)
                .toList();
    }

    public void addSubtask(Long id, SubtaskDTO subtaskDTO) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        Subtask subtask = subtaskMapper.toEntity(subtaskDTO, task);
        subtaskRepository.save(subtask);
    }


    public void editSubtask(Long id, SubtaskDTO subtaskDTO) {
        Subtask subtask = subtaskRepository.findById(id).orElseThrow(NotFoundException::new);
        subtask.setSubtaskTitle(subtaskDTO.getSubtaskTitle());
        subtask.setDeadline(subtaskDTO.getDeadline());
    }


    public void deleteSubtask(Long id) {
        Subtask subtask = subtaskRepository.findById(id).orElseThrow(NotFoundException::new);
        subtaskRepository.delete(subtask);
    }


}

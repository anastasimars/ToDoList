package com.ToDoList.model.service;

import com.ToDoList.model.repository.TaskRepository;
import com.ToDoList.model.repository.dto.TaskDTO;
import com.ToDoList.model.repository.entity.Task;
import com.ToDoList.model.service.mapping.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;

    public void addTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        taskRepository.save(task);
    }

    public TaskDTO findTaskById(Long id) {
        return taskRepository.findById(id)
                .map(task -> taskMapper.fromEntity(task))
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(task -> taskMapper.fromEntity(task))
                .toList();
    }

    public void editTask(TaskDTO taskDTO, Long id) {
        Optional<Task> byId = taskRepository.findById(id);

        Task task = byId.orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTaskTitle(taskDTO.getTaskTitle());
        task.setDeadline(taskDTO.getDeadline());
        task.setSubtasks(taskDTO.getSubtasks());
        task.setStatus(taskDTO.isStatus());
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Optional<Task> byId = taskRepository.findById(id);
        Task task = byId.orElseThrow(RuntimeException::new);
        taskRepository.delete(task);
    }

    public List<TaskDTO> sortedByTime() {
        return taskRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Task::getDeadline))
                .map(task -> taskMapper.fromEntity(task))
                .toList();

    }

}

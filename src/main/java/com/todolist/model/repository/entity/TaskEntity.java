package com.todolist.model.repository.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@Entity
@Getter
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tech_id", unique = true, nullable = false)
    private UUID techId;

    @Column(name = "task_title", nullable = false)
    private String taskTitle;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SubtaskEntity> subtasks = new ArrayList<>();

    @Column(name = "status", nullable = false)
    private boolean status = false;

    public void addSubtask(SubtaskEntity subtaskEntity) {
        subtasks.add(subtaskEntity);
        subtaskEntity.joinTask(this);
    }

    public void updateStatus() {
        this.status = this.subtasks != null && !this.subtasks.isEmpty() &&
                this.subtasks.stream().allMatch(SubtaskEntity::isStatus);
    }

    public void updateTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void updateDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Builder
    public TaskEntity(UUID techId, String taskTitle, LocalDate deadline, List<SubtaskEntity> subtasks, boolean status) {
        this.techId = techId;
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.subtasks = subtasks;
        this.status = status;
    }

    @PrePersist
    public void initializeUUID() {
        if (techId == null) {
            techId = UUID.randomUUID();
        }
    }
}

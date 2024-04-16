package com.todolist.model.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "subtask")
public class SubtaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "subtask_title", nullable = false)
    private String subtaskTitle;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;
    @Column(name = "status", nullable = false)
    private boolean status = false;

    public void updateSubtaskTitle(String subtaskTitle) {
        this.subtaskTitle = subtaskTitle;
    }

    public void updateStatus(boolean newStatus) {
        this.status = newStatus;
    }

    public void joinTask(TaskEntity task) {
        this.task = task;
    }
}


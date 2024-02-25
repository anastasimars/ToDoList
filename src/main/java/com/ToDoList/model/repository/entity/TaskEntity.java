package com.ToDoList.model.repository.entity;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_title", nullable = false)
    private String taskTitle;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @OneToMany (mappedBy = "task", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<SubtaskEntity> subtasks = new ArrayList<>();

    @Column(name = "status", nullable = false)
    private boolean status = false;

    public void addSubtask(SubtaskEntity subtaskEntity){
        subtasks.add(subtaskEntity);
        subtaskEntity.joinTask(this);
    }
    public void updateStatus() {
        this.status = subtasks.stream()
                .allMatch(SubtaskEntity::isStatus);
    }

    public void updateTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void updateDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}

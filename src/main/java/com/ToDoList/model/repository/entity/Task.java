package com.ToDoList.model.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "task_title")
    private String taskTitle;
    @Column(name = "deadline")
    private LocalDate deadline;
    @OneToMany (mappedBy = "task")
    private List<Subtask> subtasks;
    @Column(name = "status")
    private boolean status;
}

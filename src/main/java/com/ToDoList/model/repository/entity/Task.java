package com.ToDoList.model.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "task_title")
    private String taskTitle;
    @OneToMany
    private List<Subtask> subtasks;
}

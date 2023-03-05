package com.ToDoList.model.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subtask")
public class Subtask {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "subtask_title")
    private String subtaskTitle;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
    @Column(name = "status")
    private boolean status;

}

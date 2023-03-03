package com.ToDoList.model.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
}

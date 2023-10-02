package com.ToDoList.model.repository.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "subtask")
public class SubtaskEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "subtask_title")
    private String subtaskTitle;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;
    @Column(name = "status")
    private boolean status;

}

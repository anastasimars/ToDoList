package com.ToDoList.model.repository;

import com.ToDoList.model.repository.entity.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtaskRepository extends JpaRepository<Subtask, Integer> {
}

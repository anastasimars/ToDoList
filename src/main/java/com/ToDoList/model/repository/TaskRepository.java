package com.ToDoList.model.repository;

import com.ToDoList.model.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

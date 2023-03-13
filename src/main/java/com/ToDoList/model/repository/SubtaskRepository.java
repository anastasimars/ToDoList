package com.ToDoList.model.repository;

import com.ToDoList.model.repository.entity.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
    @Query("SELECT s FROM Subtask s WHERE s.task.id = :id")
    List<Subtask> findAllByTaskId(Long id);
}

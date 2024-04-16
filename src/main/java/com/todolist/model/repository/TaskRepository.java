package com.todolist.model.repository;

import com.todolist.model.repository.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("SELECT t FROM TaskEntity t JOIN FETCH t.subtasks WHERE t.id = :taskId")
    Optional<TaskEntity> findTaskWithSubtasksById(Long taskId);
}

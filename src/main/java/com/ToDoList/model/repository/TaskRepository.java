package com.ToDoList.model.repository;

import com.ToDoList.model.repository.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("SELECT t FROM TaskEntity t JOIN FETCH t.subtasks WHERE t.id = :taskId")
    TaskEntity findTaskWithSubtasksById(Long taskId);
}

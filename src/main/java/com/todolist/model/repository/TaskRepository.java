package com.todolist.model.repository;

import com.todolist.model.repository.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    @Query("SELECT t FROM TaskEntity t JOIN FETCH t.subtasks WHERE t.techId = :techId")
    Optional<TaskEntity> findByTechIdWithSubtasks(@Param("techId") UUID techId);
    @Query("SELECT t FROM TaskEntity t JOIN FETCH t.subtasks")
    List<TaskEntity> findAllWithSubtasks();
}

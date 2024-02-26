package com.ToDoList.model.repository;

import com.ToDoList.model.repository.entity.SubtaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtaskRepository extends JpaRepository<SubtaskEntity, Long> {
    @Query("SELECT s FROM SubtaskEntity s WHERE s.task.id = :id")
    List<SubtaskEntity> findAllByTaskId(@Param("id")Long id);
}

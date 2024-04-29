package com.todolist.model.repository;

import com.todolist.model.repository.entity.SubtaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubtaskRepository extends JpaRepository<SubtaskEntity, Long> {

    @Query("SELECT s FROM SubtaskEntity s WHERE s.techId = techId")
    Optional<SubtaskEntity> findByTechId(@Param("techId") UUID techId);
}

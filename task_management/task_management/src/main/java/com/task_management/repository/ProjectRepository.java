package com.task_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task_management.entity.Projects;

public interface ProjectRepository extends JpaRepository<Projects, Long> {
    List<Projects> findByUserId(Long userId);
}



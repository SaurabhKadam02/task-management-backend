package com.task_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task_management.entity.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findByProjects_Id(Long projectId);
}

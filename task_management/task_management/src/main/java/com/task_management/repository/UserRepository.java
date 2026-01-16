package com.task_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task_management.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
}


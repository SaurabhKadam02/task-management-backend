package com.task_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_management.dto.ApiResponse;
import com.task_management.dto.ProjectDto;
import com.task_management.entity.Projects;
import com.task_management.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    @Autowired private ProjectService service;

    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse<Projects>> createProject(
            @PathVariable Long userId,
            @RequestBody ProjectDto dto) {

        ApiResponse<Projects> response = service.createProject(userId, dto);
        System.out.println("response :"+response.getMessage());
        if (!response.isSuccess()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<List<Projects>>> list(@PathVariable Long userId) {
         ApiResponse<List<Projects>> response = service.getProjects(userId);
         return ResponseEntity
                 .status(HttpStatus.OK)
                 .body(response);
        
    }
}


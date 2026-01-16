package com.task_management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_management.dto.ApiResponse;
import com.task_management.dto.ProjectDto;
import com.task_management.entity.Projects;
import com.task_management.entity.User;
import com.task_management.repository.ProjectRepository;
import com.task_management.repository.UserRepository;
import com.task_management.service.ProjectService;

@Service
	public class ProjectServiceImpl implements ProjectService {

	    @Autowired private ProjectRepository repo;
	    @Autowired private UserRepository userRepo;

	    @Override
	    public ApiResponse<Projects> createProject(Long userId, ProjectDto dto) {

	        if (dto.getName() == null || dto.getName().isBlank()) {
	            return new ApiResponse<>(false,"Project name cannot be empty", null);
	        }
	        if (dto.getDescription()== null || dto.getDescription().isBlank()) {
	            return new ApiResponse<>(false,"Project description cannot be empty", null);
	        }
	        if (dto.getDuration() <= 0) {
	            return new ApiResponse<>(false,"Project duration cannot be zero ", null);
	        }

	        Optional<User> userOpt = userRepo.findById(userId);
	        if (userOpt.isEmpty()) {
	            return new ApiResponse<>(false,"User not found", null);
	        }

	        User user = userOpt.get();

	        Projects project = new Projects();
	        project.setName(dto.getName());
	        project.setUser(user);
	        project.setDescription(dto.getDescription());
	        project.setDuration(dto.getDuration());
	        Projects savedProject = repo.save(project);

	        return new ApiResponse<>(true,"Project created successfully.", savedProject);
	    }


	    @Override
	    public ApiResponse<List<Projects>> getProjects(Long userId) {
	    	List<Projects> projects = repo.findByUserId(userId);

	        if (projects.isEmpty()) {
	            return new ApiResponse<>(true, "No projects found", projects);
	        }

	        return new ApiResponse<>(true, "success", projects);
	    }
	}


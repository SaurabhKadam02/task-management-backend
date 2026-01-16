package com.task_management.service;

import java.util.List;

import com.task_management.dto.ApiResponse;
import com.task_management.dto.ProjectDto;
import com.task_management.entity.Projects;

public interface ProjectService {

	ApiResponse<Projects> createProject(Long userId, ProjectDto dto);
	ApiResponse<List<Projects>> getProjects(Long userId);
}

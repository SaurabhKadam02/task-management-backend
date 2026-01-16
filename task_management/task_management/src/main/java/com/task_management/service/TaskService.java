package com.task_management.service;

import java.util.List;

import com.task_management.dto.ApiResponse;
import com.task_management.dto.TaskDto;

public interface TaskService {

	ApiResponse<TaskDto> createTask(Long projectId, TaskDto dto);

	ApiResponse<List<TaskDto>>  getTasks(Long projectId);

	ApiResponse<TaskDto> updateStatus(Long taskId, String status);

	ApiResponse<Void> deleteTask(Long taskId);
}

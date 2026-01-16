package com.task_management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_management.dto.ApiResponse;
import com.task_management.dto.TaskDto;
import com.task_management.entity.Projects;
import com.task_management.entity.Tasks;
import com.task_management.repository.ProjectRepository;
import com.task_management.repository.TaskRepository;
import com.task_management.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository repo;
	@Autowired
	private ProjectRepository projectRepo;

	private static final List<String> ALLOWED_STATUS = List.of("TO_DO", "IN_PROGRESS", "DONE");

	@Override
	public ApiResponse<TaskDto> createTask(Long projectId, TaskDto dto) {

		if (projectId == null) {
			return new ApiResponse<>(false, "Project id cannot be null", null);
		}

		if (dto.getTitle() == null || dto.getTitle().isBlank()) {
			return new ApiResponse<>(false, "Task title cannot be empty", null);
		}

		Optional<Projects> projectOpt = projectRepo.findById(projectId);
		if (projectOpt.isEmpty()) {
			return new ApiResponse<>(false, "Project not found", null);
		}

		Tasks task = new Tasks();
		task.setTitle(dto.getTitle());
		task.setStatus("to-do");
		task.setProjects(projectOpt.get());

		Tasks saved = repo.save(task);

		TaskDto responseDto = new TaskDto(saved.getId(), saved.getTitle(), saved.getStatus());

		return new ApiResponse<>(true, "Task created successfully", responseDto);
	}

	@Override
	public ApiResponse<List<TaskDto>> getTasks(Long projectId) {

		if (projectId == null) {
			return new ApiResponse<>(false, "Project id cannot be null", null);
		}

		if (!projectRepo.existsById(projectId)) {
			return new ApiResponse<>(false, "Project not found", null);
		}

		List<TaskDto> tasks = repo.findByProjects_Id(projectId).stream()
				.map(t -> new TaskDto(t.getId(), t.getTitle(), t.getStatus())).toList();

		return new ApiResponse<>(true, "success", tasks);
	}

	@Override
	public ApiResponse<TaskDto> updateStatus(Long taskId, String status) {

		if (taskId == null) {
			return new ApiResponse<>(false, "Task id cannot be null", null);
		}

		if (status == null || status.isBlank()) {
			return new ApiResponse<>(false, "Status cannot be empty", null);
		}

		if (!ALLOWED_STATUS.contains(status)) {
			return new ApiResponse<>(false, "Invalid task status", null);
		}

		Optional<Tasks> taskOpt = repo.findById(taskId);
		if (taskOpt.isEmpty()) {
			return new ApiResponse<>(false, "Task not found", null);
		}

		Tasks task = taskOpt.get();
		task.setStatus(status);

		Tasks updated = repo.save(task);

		TaskDto dto = new TaskDto(updated.getId(), updated.getTitle(), updated.getStatus());

		return new ApiResponse<>(true, "success", dto);
	}

	@Override
	public ApiResponse<Void> deleteTask(Long taskId) {

		if (taskId == null) {
			return new ApiResponse<>(false, "Task id cannot be null", null);
		}

		Optional<Tasks> taskOpt = repo.findById(taskId);
		if (taskOpt.isEmpty()) {
			return new ApiResponse<>(false, "Task not found", null);
		}

		repo.delete(taskOpt.get());

		return new ApiResponse<>(true, "success", null);
	}

}

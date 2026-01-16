package com.task_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task_management.dto.ApiResponse;
import com.task_management.dto.TaskDto;
import com.task_management.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

	@Autowired
	private TaskService service;

	@PostMapping("/{projectId}")
	public ResponseEntity<ApiResponse<TaskDto>> create(@PathVariable Long projectId, @RequestBody TaskDto dto) {

		ApiResponse<TaskDto> response = service.createTask(projectId, dto);

		if (!response.isSuccess()) {
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<ApiResponse<List<TaskDto>>> list(@PathVariable Long projectId) {

		ApiResponse<List<TaskDto>> response = service.getTasks(projectId);

		if (!response.isSuccess()) {
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{taskId}")
	public ResponseEntity<ApiResponse<TaskDto>> update(@PathVariable Long taskId, @RequestParam String status) {

		ApiResponse<TaskDto> response = service.updateStatus(taskId, status);

		if (!response.isSuccess()) {
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{taskId}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long taskId) {

		ApiResponse<Void> response = service.deleteTask(taskId);

		if (!response.isSuccess()) {
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

}

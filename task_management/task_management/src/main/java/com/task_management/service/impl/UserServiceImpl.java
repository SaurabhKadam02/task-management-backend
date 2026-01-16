package com.task_management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_management.dto.ApiResponse;
import com.task_management.dto.UserDto;
import com.task_management.entity.User;
import com.task_management.repository.UserRepository;
import com.task_management.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	

	public ApiResponse<User> register(UserDto dto) {
		System.out.println("register.... ");
		if (dto.getUsername() == null || dto.getUsername().isBlank()) {
			return new ApiResponse<>(false,"Username cannot be empty", null);
		}

		if (repo.findByUsername(dto.getUsername()) != null) {
			return new ApiResponse<>(false,"Username already exists", null);
		}

		if (dto.getPassword() == null || dto.getPassword().isBlank()) {
			return new ApiResponse<>(false,"Password cannot be empty", null);
		}

		if (dto.getEmail() == null || dto.getEmail().isBlank()) {
			return new ApiResponse<>(false,"Email cannot be empty", null);
		}

		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
		User savedUser = repo.save(user);
		System.out.println("user registered :" + savedUser.getId());
		return new ApiResponse<>(true,"User registered successfully", savedUser);
	}

	@Override
	public ApiResponse<User> login(UserDto dto) {
		System.out.println("Login.... ");
		if (dto.getUsername() == null || dto.getUsername().isBlank()) {
			return new ApiResponse<>(false,"Username cannot be empty", null);
		}

		if (dto.getPassword() == null || dto.getPassword().isBlank()) {
			return new ApiResponse<>(false,"Password cannot be empty", null);
		}

		User user = repo.findByUsername(dto.getUsername());

		if (user == null) {
			return new ApiResponse<>(false,"Invalid username or password", null);
		}
		if (!user.getPassword().equals(dto.getPassword())) {
			return new ApiResponse<>(false,"Invalid username or password", null);
		}

		return new ApiResponse<>(true,"SUCCESS", user);
	}


}

package com.task_management.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_management.dto.ApiResponse;
import com.task_management.dto.UserDto;
import com.task_management.entity.User;
import com.task_management.security.JwtUtil;
import com.task_management.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UsersController {

	@Autowired
	private UserService service;
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDto dto) {
		try {
			ApiResponse<User> register = service.register(dto);
			if (!register.isSuccess()) {
				return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(false, register.getMessage(), null));
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "success", register));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto dto) throws BadRequestException {
		ApiResponse<User> response = service.login(dto);

		if (!response.isSuccess()) {
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(false,response.getMessage(), null));
		}

		User user = response.getData();
		String token = jwtUtil.generateToken(user.getUsername());

		Map<String, Object> data = new HashMap<>();
		data.put("token", token);
		data.put("username", user.getUsername());
		data.put("userId", user.getId());

		return ResponseEntity.ok(new ApiResponse<>(true,"Login successful", data));
	}
}

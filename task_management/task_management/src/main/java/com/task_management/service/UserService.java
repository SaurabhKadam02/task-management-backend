package com.task_management.service;

import com.task_management.dto.ApiResponse;
import com.task_management.dto.UserDto;
import com.task_management.entity.User;

public interface UserService {

	ApiResponse<User> register(UserDto dto);

	ApiResponse<User> login(UserDto dto);

}

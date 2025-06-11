package com.example.freelance_resource_backend.dto.request.user;

import lombok.Getter;

import com.example.freelance_resource_backend.enums.UserRole;

@Getter
public class GetUserRequest {
	private String email;
	private UserRole role;
}

package com.example.freelance_resource_backend.dto.request.login;

import lombok.Getter;

import com.example.freelance_resource_backend.enums.UserRole;

@Getter
public class LoginRequest {
	private String email;
	private String password;
	private UserRole role;
}

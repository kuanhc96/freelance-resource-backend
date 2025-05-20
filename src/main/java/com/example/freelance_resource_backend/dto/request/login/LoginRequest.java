package com.example.freelance_resource_backend.dto.request.login;

import lombok.Getter;

@Getter
public class LoginRequest {
	private String email;
	private String password;
}

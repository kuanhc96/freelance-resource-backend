package com.example.freelance_resource_backend.dto.response.login;

import java.util.List;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.UserRole;

@Data
@Builder
public class LoginResponse {
	private String userGUID;
	private String email;
	private UserRole role;
//	private Long tokenDuration;
}

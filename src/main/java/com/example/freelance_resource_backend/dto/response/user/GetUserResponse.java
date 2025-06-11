package com.example.freelance_resource_backend.dto.response.user;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.UserRole;

@Builder
@Data
public class GetUserResponse {
	private String userGUID;
	private String name;
	private String email;
	private UserRole role;
}

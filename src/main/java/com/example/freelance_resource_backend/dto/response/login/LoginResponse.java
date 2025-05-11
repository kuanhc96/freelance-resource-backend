package com.example.freelance_resource_backend.dto.response.login;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
	private Boolean success;
	private String userId;
	private List<String> subscribedIds;
	private String role;
}

package com.example.freelance_resource_backend.dto.request.user;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.Gender;
import com.example.freelance_resource_backend.enums.UserRole;
import com.example.freelance_resource_backend.enums.UserStatus;

@Data
@Builder
public class CreateUserRequest {
	private String email;
	private String password;
	private UserRole role;
	private String name;
	private LocalDate birthday;
	private Gender gender;
	private String description;
	private String profilePicture;
}

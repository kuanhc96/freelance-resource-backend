package com.example.freelance_resource_backend.dto.response.user;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.Gender;
import com.example.freelance_resource_backend.enums.UserStatus;

@Data
@Builder
public class CreateUserResponse {
	private String userGUID;
	private String name;
	private String email;
	private LocalDate birthday;
	private Gender gender;
	private String description;
	private UserStatus status;
}

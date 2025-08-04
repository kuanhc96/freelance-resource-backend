package com.example.freelance_resource_backend.dto.response.user;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.Gender;
import com.example.freelance_resource_backend.enums.UserRole;

@Builder
@Data
public class GetUserResponse {
	private String userGUID;
	private String name;
	private String email;
	private UserRole role;
	private Gender gender;
	private String description;
	private LocalDate birthday;
	private String profilePicture;
}

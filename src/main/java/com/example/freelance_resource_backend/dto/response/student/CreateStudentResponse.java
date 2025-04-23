package com.example.freelance_resource_backend.dto.response.student;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.Gender;
import com.example.freelance_resource_backend.enums.UserStatus;

@Data
@Builder
public class CreateStudentResponse {
	private String studentGUID;
	private String studentName;
	private String email;
	private LocalDate birthday;
	private UserStatus status;
	private Gender gender;
	private String description;
}

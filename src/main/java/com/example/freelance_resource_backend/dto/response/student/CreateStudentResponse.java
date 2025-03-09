package com.example.freelance_resource_backend.dto.response.student;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.UserStatus;

@Data
@Builder
public class CreateStudentResponse {
	private String studentGUID;
	private String studentName;
	private String email;
	private Integer birthYear;
	private Integer birthMonth;
	private Integer birthDay;
	private UserStatus status;
}

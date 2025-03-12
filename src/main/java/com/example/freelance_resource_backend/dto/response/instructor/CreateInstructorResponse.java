package com.example.freelance_resource_backend.dto.response.instructor;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.UserStatus;

@Data
@Builder
public class CreateInstructorResponse {
	private String instructorGUID;
	private String instructorName;
	private String email;
	private Integer birthYear;
	private Integer birthMonth;
	private Integer birthDay;
	private UserStatus status;
}

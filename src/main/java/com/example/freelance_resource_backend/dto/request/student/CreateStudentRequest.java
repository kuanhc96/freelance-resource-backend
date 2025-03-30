package com.example.freelance_resource_backend.dto.request.student;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.Gender;

@Data
@Builder
public class CreateStudentRequest {
	private String email;
	private String password;
	private String studentName;
	private Integer birthYear;
	private Integer birthMonth;
	private Integer birthDay;
	private Gender gender;
	private String description;
}

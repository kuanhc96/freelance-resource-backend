package com.example.freelance_resource_backend.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateStudentRequest {
	private String email;
	private String password;
	private String studentName;
	private Integer birthYear;
	private Integer birthMonth;
	private Integer birthDay;
}

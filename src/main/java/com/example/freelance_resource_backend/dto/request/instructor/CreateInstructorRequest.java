package com.example.freelance_resource_backend.dto.request.instructor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateInstructorRequest {
	private String email;
	private String password;
	private String instructorName;
	private Integer birthYear;
	private Integer birthMonth;
	private Integer birthDay;
}

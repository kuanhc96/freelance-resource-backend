package com.example.freelance_resource_backend.dto.request.instructor;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.Gender;

@Data
@Builder
public class CreateInstructorRequest {
	private String email;
	private String password;
	private String name;
	private LocalDate birthday;
	private Gender gender;
	private String description;
}

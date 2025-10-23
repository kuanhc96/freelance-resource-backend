package com.example.freelance_resource_backend.controller.integration.helper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.freelance_resource_backend.dto.request.user.CreateUserRequest;
import com.example.freelance_resource_backend.enums.Gender;
import com.example.freelance_resource_backend.enums.UserRole;

@Component
public class TestHelper {
	public CreateUserRequest createInstructorRequestStub() {
		CreateUserRequest request = CreateUserRequest.builder()
				.email("instructor.email" + System.currentTimeMillis() + "@ittest.com")
				.role(UserRole.INSTRUCTOR)
				.birthday(LocalDate.now())
				.gender(Gender.MALE)
				.name("testInstructor")
				.password("Test1234")
				.build();
		return request;
	}

	public CreateUserRequest createStudentRequestStub() {
		CreateUserRequest request = CreateUserRequest.builder()
				.email("student.email" + System.currentTimeMillis() + "@ittest.com")
				.role(UserRole.STUDENT)
				.birthday(LocalDate.now())
				.gender(Gender.MALE)
				.name("testStudent")
				.password("Test1234")
				.build();
		return request;
	}
}

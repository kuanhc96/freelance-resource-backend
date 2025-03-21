package com.example.freelance_resource_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.instructor.CreateInstructorRequest;
import com.example.freelance_resource_backend.dto.response.instructor.CreateInstructorResponse;
import com.example.freelance_resource_backend.entities.InstructorEntity;
import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.enums.UserRole;
import com.example.freelance_resource_backend.service.FreelanceUserDetailsService;
import com.example.freelance_resource_backend.service.InstructorService;
import com.example.freelance_resource_backend.service.StudentService;

@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorController {

	private final FreelanceUserDetailsService userDetailsService;

	private final InstructorService instructorService;

	@PostMapping("/createInstructor")
	public ResponseEntity<CreateInstructorResponse> createInstructor(@RequestBody CreateInstructorRequest request) {
		String email = request.getEmail();
		String password = request.getPassword();
		UserEntity userEntity = userDetailsService.createUser(email, password, UserRole.INSTRUCTOR);
		String instructorGUID = userEntity.getUserGUID();
		InstructorEntity instructorEntity = instructorService.createInstructor(instructorGUID, request);
		return ResponseEntity.ok(CreateInstructorResponse.builder()
				.email(request.getEmail())
				.instructorName(request.getInstructorName())
				.birthYear(request.getBirthYear())
				.birthMonth(request.getBirthMonth())
				.birthDay(request.getBirthDay())
				.status(instructorEntity.getStatus())
				.build());
	}
}

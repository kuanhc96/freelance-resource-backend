package com.example.freelance_resource_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.CreateStudentRequest;
import com.example.freelance_resource_backend.dto.response.CreateStudentResponse;
import com.example.freelance_resource_backend.entities.StudentEntity;
import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.enums.UserRole;
import com.example.freelance_resource_backend.service.FreelanceUserDetailsService;
import com.example.freelance_resource_backend.service.StudentService;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

	private final FreelanceUserDetailsService userDetailsService;

	private final StudentService studentService;

	@PostMapping("/createStudent")
	public ResponseEntity<CreateStudentResponse> createStudent(@RequestBody CreateStudentRequest request) {
		String email = request.getEmail();
		String password = request.getPassword();
		UserEntity userEntity = userDetailsService.createUser(email, password, UserRole.STUDENT);
		String studentGUID = userEntity.getUserGUID();
		StudentEntity studentEntity = studentService.createStudent(studentGUID, request);

		return ResponseEntity.ok(CreateStudentResponse.builder()
				.studentGUID(studentEntity.getStudentGUID())
				.studentName(studentEntity.getStudentName())
				.email(studentEntity.getEmail())
				.birthYear(studentEntity.getBirthYear())
				.birthMonth(studentEntity.getBirthMonth())
				.birthDay(studentEntity.getBirthDay())
				.status(studentEntity.getStatus())
				.build());
	}

}

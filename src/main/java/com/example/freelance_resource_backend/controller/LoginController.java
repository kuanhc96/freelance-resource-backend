package com.example.freelance_resource_backend.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.response.login.LoginResponse;
import com.example.freelance_resource_backend.entities.StudentEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.service.StudentService;

@RestController
@RequiredArgsConstructor
public class LoginController {
	private final StudentService studentService;

	@GetMapping("/testLogin")
	public LoginResponse testLogin(Authentication authentication) {
		System.out.println(authentication.getName());
		boolean success = false;
		String studentGUID = null;
		List<String> subscribedIds = new LinkedList<>();
		String role = null;
		try {
			StudentEntity studentEntity = studentService.getStudentByEmail(authentication.getName());
			success = true;
			studentGUID = studentEntity.getStudentGUID();
			return LoginResponse.builder()
					.success(success)
					.userId(studentGUID)
					.build();
		} catch(ResourceNotFoundException e) {
			return LoginResponse.builder().success(success).build();

		}
	}
}

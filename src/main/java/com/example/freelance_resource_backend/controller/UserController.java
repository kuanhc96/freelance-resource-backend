package com.example.freelance_resource_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.user.CreateUserRequest;
import com.example.freelance_resource_backend.dto.request.user.GetUserRequest;
import com.example.freelance_resource_backend.dto.response.user.CreateUserResponse;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.service.FreelanceUserDetailsService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final FreelanceUserDetailsService userDetailsService;

	@PostMapping
	public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
		return ResponseEntity.ok(userDetailsService.createUser(request));
	}

	@GetMapping("/{userGUID}")
	public ResponseEntity<GetUserResponse> getUserByUserGUID(@PathVariable String userGUID) throws ResourceNotFoundException {
		GetUserResponse userResponse = userDetailsService.getUserByUserGUID(userGUID);
		return ResponseEntity.ok(userResponse);
	}

	@GetMapping
	public ResponseEntity<GetUserResponse> getUserByUserGUID(@RequestBody GetUserRequest request) throws ResourceNotFoundException {
		GetUserResponse userResponse = userDetailsService.getUserByEmailAndRole(request.getEmail(), request.getRole());
		return ResponseEntity.ok(userResponse);
	}
}

package com.example.freelance_resource_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.constants.ApplicationConstants;
import com.example.freelance_resource_backend.dto.request.instructor.CreateInstructorRequest;
import com.example.freelance_resource_backend.dto.response.instructor.CreateInstructorResponse;
import com.example.freelance_resource_backend.dto.response.instructor.GetInstructorResponse;
import com.example.freelance_resource_backend.entities.InstructorEntity;
import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.enums.UserRole;
import com.example.freelance_resource_backend.service.FreelanceUserDetailsService;
import com.example.freelance_resource_backend.service.InstructorService;

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
				.instructorName(request.getName())
				.gender(request.getGender())
				.description(request.getDescription())
				.birthday(request.getBirthday())
				.status(instructorEntity.getStatus())
				.build());
	}

	@GetMapping("/getAllInstructors")
	public ResponseEntity<List<GetInstructorResponse>> getAllInstructors() {
		List<GetInstructorResponse> instructorList = instructorService.getAllInstructors();
		return ResponseEntity.ok(instructorList);
	}

	@GetMapping("/getSubscribedInstructors/{studentGUID}")
	@PreAuthorize(ApplicationConstants.ROLE_INSTRUCTOR)
	public ResponseEntity<List<String>> getSubscribedInstructors(@PathVariable String studentGUID) {
		List<String> instructorGUIDs = List.of("test");
		return ResponseEntity.ok(instructorGUIDs);
	}
}

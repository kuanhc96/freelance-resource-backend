package com.example.freelance_resource_backend.controller;

import static com.example.freelance_resource_backend.constants.ApplicationConstants.INSTRUCTOR;
import static com.example.freelance_resource_backend.constants.ApplicationConstants.INSTRUCTOR_OR_STUDENT;
import static com.example.freelance_resource_backend.constants.ApplicationConstants.INTEGRATION_TEST;
import static com.example.freelance_resource_backend.constants.ApplicationConstants.STUDENT;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.client.AuthServerFeignClient;
import com.example.freelance_resource_backend.dto.request.user.CreateUserRequest;
import com.example.freelance_resource_backend.dto.request.user.GetUserRequest;
import com.example.freelance_resource_backend.dto.response.user.CreateUserResponse;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;
import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.UserRepository;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserRepository userRepository;
	private final AuthServerFeignClient authServerFeignClient;

	@PostMapping("/create")
	@PreAuthorize(INTEGRATION_TEST)
	public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request){
		return authServerFeignClient.createUser(request);
	}

	@GetMapping("/test")
	@PreAuthorize(STUDENT)
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("User service is running");
	}

	@GetMapping("/{userGUID}")
	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public ResponseEntity<GetUserResponse> getUserByUserGUID(@PathVariable String userGUID) throws ResourceNotFoundException {
		Optional<UserEntity> optionalUserEntity = userRepository.getUserByUserGUID(userGUID);
		if (optionalUserEntity.isPresent()) {
			UserEntity userEntity = optionalUserEntity.get();
			return ResponseEntity.ok(
					GetUserResponse.builder()
							.userGUID(userEntity.getUserGUID())
							.email(userEntity.getEmail())
							.role(userEntity.getRole())
							.name(userEntity.getName())
							.gender(userEntity.getGender())
							.description(userEntity.getDescription())
							.birthday(userEntity.getBirthday())
							.profilePicture(userEntity.getProfilePicture())
							.build()
			);
		} else {
			throw new ResourceNotFoundException("User with GUID " + userGUID + " not found.");
		}
	}

	@GetMapping
	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public ResponseEntity<GetUserResponse> getUserByUserEmailAndRole(@RequestBody GetUserRequest request) throws ResourceNotFoundException {
		Optional<UserEntity> optionalUserEntity = userRepository.getUserByEmailAndRole(request.getEmail(), request.getRole());
		if (optionalUserEntity.isPresent()) {
			UserEntity userEntity = optionalUserEntity.get();
			return ResponseEntity.ok(
					GetUserResponse.builder()
							.userGUID(userEntity.getUserGUID())
							.email(userEntity.getEmail())
							.role(userEntity.getRole())
							.name(userEntity.getName())
							.birthday(userEntity.getBirthday())
							.description(userEntity.getDescription())
							.gender(userEntity.getGender())
							.profilePicture(userEntity.getProfilePicture())
							.build()
			);
		} else {
			throw new ResourceNotFoundException("User with email " + request.getEmail() + " and role " + request.getRole() + " not found.");
		}
	}
}

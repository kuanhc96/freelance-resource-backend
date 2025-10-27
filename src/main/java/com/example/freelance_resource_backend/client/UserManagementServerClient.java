package com.example.freelance_resource_backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.freelance_resource_backend.config.OAuth2FeignConfig;
import com.example.freelance_resource_backend.dto.request.user.CreateUserRequest;
import com.example.freelance_resource_backend.dto.response.user.CreateUserResponse;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;

@FeignClient(name="freelance-user-management-server", configuration = OAuth2FeignConfig.class)
public interface UserManagementServerClient {

	@PostMapping(value = "/user/create", consumes = "application/json")
	ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request);

	@DeleteMapping(value = "/user/delete/{userGUID}")
	ResponseEntity<Void> deleteUser(@PathVariable String userGUID);

	@GetMapping(value = "/user/{userGUID}")
	ResponseEntity<GetUserResponse> getUserByUserGUID(@PathVariable String userGUID);

	@GetMapping(value = "/user")
	ResponseEntity<GetUserResponse> getUserByEmailAndRole(@RequestParam String email, @RequestParam String role);

}

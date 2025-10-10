package com.example.freelance_resource_backend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.freelance_resource_backend.dto.request.user.CreateUserRequest;
import com.example.freelance_resource_backend.dto.response.user.CreateUserResponse;

@FeignClient("freelance-authserver")
public interface AuthServerFeignClient {

	@PostMapping(value = "/user/create", consumes = "application/json")
	public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request);
}

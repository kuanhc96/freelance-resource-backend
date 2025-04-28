package com.example.freelance_resource_backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	@GetMapping("/testLogin")
	public boolean testLogin(Authentication authentication) {
		System.out.println(authentication.getName());
		return true;
	}
}

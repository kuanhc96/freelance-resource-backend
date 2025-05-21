package com.example.freelance_resource_backend.controller;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.constants.ApplicationConstants;
import com.example.freelance_resource_backend.dto.request.login.LoginRequest;
import com.example.freelance_resource_backend.dto.response.login.LoginResponse;
import com.example.freelance_resource_backend.entities.StudentEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.service.StudentService;

@RestController
@RequiredArgsConstructor
public class LoginController {
	private final StudentService studentService;
	private final AuthenticationManager authenticationManager;
	private final Environment env;

	@PostMapping("/apiLogin")
	public ResponseEntity<LoginResponse> apiLogin(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
		String jwt = "";
		boolean success = false;
		String studentGUID = null;
		String role = null;
		Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getEmail(), loginRequest.getPassword());
		try {
			StudentEntity studentEntity = studentService.getStudentByEmail(authentication.getName());
			success = true;
			studentGUID = studentEntity.getStudentGUID();
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(LoginResponse.builder().success(success).build());
		}

		Authentication authenticationResponse = authenticationManager.authenticate(authentication);
		if (null != authenticationResponse && authenticationResponse.isAuthenticated()) {
			if (null != env) {
				String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
				SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
				jwt = Jwts.builder()
						.setIssuer("FreelanceApp")
						.setSubject("JWT Token")
						.claim("email", authentication.getName())
						.claim("authorities", authentication.getAuthorities().stream().map(
								GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
						.setIssuedAt(new Date())
						.setExpiration(new Date(new Date().getTime() + 30000000))
						.signWith(secretKey)
						.compact();
				Cookie cookie = new Cookie("loginToken", jwt);
				cookie.setHttpOnly(true);
				cookie.setSecure(true);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER, jwt)
				.body(LoginResponse.builder()
						.success(true)
						.userId(studentGUID)
						.role(null)
						.jwt(jwt)
						.build()
				);
	}

	@GetMapping("/testLogin")
	public LoginResponse testLogin(Authentication authentication) {
		boolean success = false;
		String studentGUID = null;
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

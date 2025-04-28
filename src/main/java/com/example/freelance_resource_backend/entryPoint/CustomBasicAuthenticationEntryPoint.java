package com.example.freelance_resource_backend.entryPoint;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		LocalDateTime currentTime = LocalDateTime.now();
		String message = (authException != null && authException.getMessage() != null) ?
				authException.getMessage() : "Authentication failed";
		String path = request.getRequestURI();
		response.setHeader("eazybank-error-reason", "Authentication failed");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json;charset=UTF-8");

		String jsonResponse = String.format(
				"{\"timestamp\": \"%s\", \"message\": \"%s\", \"path\": \"%s\", \"status\": %d, \"error\": \"%s\"}",
				currentTime, message, path, HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()
		);
		response.getWriter().write(jsonResponse);

	}
}

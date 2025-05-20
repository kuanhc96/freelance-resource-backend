package com.example.freelance_resource_backend.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.crypto.SecretKey;

import com.example.freelance_resource_backend.constants.ApplicationConstants;

public class JwtTokenGenerationFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// Implement your JWT token generation logic here
		// For example, you can check if the request has a valid token and generate a new one if needed

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null != authentication) { // successful authentication
			Environment env = getEnvironment();
			if (env != null) {
				String jwtSecret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
				SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
				String jwt = Jwts.builder()
						.setIssuer("FreelanceApp")
						.setSubject("JWT Token")
						.claim("email", authentication.getName())
						.claim("authorities", authentication.getAuthorities().stream().map(
								GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
						.setIssuedAt(new Date())
						.setExpiration(new Date(new Date().getTime() + 30000000))
						.signWith(secretKey)
						.compact();

				response.setHeader(ApplicationConstants.JWT_HEADER, jwt);
			}

		}
		// Continue the filter chain
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// Define the conditions under which this filter should not be applied
		// For example, you can skip certain paths or methods
		String path = request.getRequestURI();
		// Skip filtering for public API endpoints
		return path.equals("/apiLogin") || path.equals("/instructor/createInstructor")
		|| path.equals("/student/createStudent") || path.equals("/forgetPassword");
	}
}

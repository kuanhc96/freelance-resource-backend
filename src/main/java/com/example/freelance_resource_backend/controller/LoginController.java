package com.example.freelance_resource_backend.controller;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.authentication.EmailPasswordRoleAuthenticationToken;
import com.example.freelance_resource_backend.constants.ApplicationConstants;
import com.example.freelance_resource_backend.dto.request.login.LoginRequest;
import com.example.freelance_resource_backend.dto.response.login.LoginResponse;
import com.example.freelance_resource_backend.enums.UserRole;

@RestController
@RequiredArgsConstructor
public class LoginController {
	private final AuthenticationManager authenticationManager;
	private final Environment env;

	@PostMapping("/checkLogin")
	public ResponseEntity<LoginResponse> checkLogin(HttpServletRequest request, HttpServletResponse response) {
		String jwt = Arrays.stream(request.getCookies())
				.filter(cookie -> "accessToken".equals(cookie.getName()))
				.map(Cookie::getValue)
				.findFirst()
				.orElse(null);
		if (null != jwt) {
			try {
				String jwtSecret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
				SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
				if (null != secretKey) {
					Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
					String userGUID = String.valueOf(claims.get("userGUID", String.class));
					String email = String.valueOf(claims.get("email", String.class));
					UserRole role = UserRole.valueOf(String.valueOf(claims.get("role", String.class)));
					String authorities = String.valueOf(claims.get("authorities", String.class));
					Authentication authentication = new EmailPasswordRoleAuthenticationToken(userGUID, email, role,
							null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
					SecurityContextHolder.getContext().setAuthentication(authentication);
					return ResponseEntity.status(HttpStatus.OK)
							.body(LoginResponse.builder()
									.userGUID(userGUID)
									.role(role)
									.email(email)
									.build()
							);
				}
				throw new BadCredentialsException("Invalid token");
			} catch (Exception e) {
				throw new BadCredentialsException("Invalid token");
			}
		} else {
			throw new BadCredentialsException("Invalid token");
		}
	}

	@PostMapping("/apiLogin")
	public ResponseEntity<LoginResponse> apiLogin(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
		String accessTokenJwt = "";
		String authorization = null;
		Authentication authentication = new EmailPasswordRoleAuthenticationToken(
				null, loginRequest.getEmail(), loginRequest.getRole(), loginRequest.getPassword()
		);

		EmailPasswordRoleAuthenticationToken authenticationResponse = (EmailPasswordRoleAuthenticationToken) authenticationManager.authenticate(authentication);
		Long expirationTimestamp = new Date().getTime() + 30000000; // 30 million milliseconds = ~8.3 hours
		if (null != authenticationResponse && authenticationResponse.isAuthenticated()) {
			if (null != env) {
				String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
				SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
				authorization = authenticationResponse.getAuthorities().stream().map(
						GrantedAuthority::getAuthority).collect(Collectors.joining(","));
				accessTokenJwt = Jwts.builder()
						.setIssuer("FreelanceApp")
						.setSubject("JWT accessToken")
						.claim("email", authenticationResponse.getName())
						.claim("userGUID", authenticationResponse.getUserGUID())
						.claim("role", authenticationResponse.getRole())
						.claim("authorities", authorization)
						.setIssuedAt(new Date())
						.signWith(secretKey)
						.compact();
				Cookie cookie = new Cookie("accessToken", accessTokenJwt);
				cookie.setHttpOnly(true);
				cookie.setSecure(true);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER, accessTokenJwt)
					.body(LoginResponse.builder()
							.userGUID(authenticationResponse.getUserGUID())
							.role(authenticationResponse.getRole())
							.email(authenticationResponse.getName())
							.expirationTimestamp(expirationTimestamp)
							.build()
					);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(LoginResponse.builder()
							.userGUID(null)
							.role(loginRequest.getRole())
							.email(loginRequest.getEmail())
							.build()
					);
		}
	}

	@PostMapping("/apiLogout")
	public ResponseEntity<Void> logout(HttpServletResponse response) {
		// Clear the JWT cookie
		clearCookie("accessToken", response, true);
		clearCookie("XSRF-TOKEN", response, false);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	private void clearCookie(String name, HttpServletResponse response, boolean httpOnly) {
		Cookie cookie = new Cookie(name, "");
		cookie.setPath("/");
		cookie.setHttpOnly(httpOnly);
		cookie.setSecure(true); // Only if you're using HTTPS
		cookie.setMaxAge(0);    // Clear the cookie
		response.addCookie(cookie);
	}
}

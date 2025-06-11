package com.example.freelance_resource_backend.authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.freelance_resource_backend.enums.UserRole;

public class EmailPasswordRoleAuthenticationToken extends UsernamePasswordAuthenticationToken {
	private final UserRole role;
	private final String userGUID;

	public EmailPasswordRoleAuthenticationToken(String userGUID, String email, UserRole role, String password) {
		super(email, password, List.of(new SimpleGrantedAuthority("ROLE_FREELANCE_APP_USER")));
		this.role = role;
		this.userGUID = userGUID;
	}

	public EmailPasswordRoleAuthenticationToken(String userGUID, String email, UserRole role, String password, Collection<GrantedAuthority> authorities) {
		super(email, password, authorities);
		this.role = role;
		this.userGUID = userGUID;
	}

	public UserRole getRole() {
		return role;
	}

	public String getUserGUID() {
		return userGUID;
	}
}

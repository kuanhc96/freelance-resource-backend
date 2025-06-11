package com.example.freelance_resource_backend.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.freelance_resource_backend.enums.UserRole;

public class FreelanceAppUserDetails extends User {
	private String userGUID;
	private UserRole role;

	public FreelanceAppUserDetails(String userGUID, String email, UserRole role, String password, Collection<? extends GrantedAuthority> authorities) {
		super(email, password, authorities);
		this.userGUID = userGUID;
		this.role = role;
	}

	public String getUserGUID() {
		return userGUID;
	}

	public UserRole getRole() {
		return role;
	}
}

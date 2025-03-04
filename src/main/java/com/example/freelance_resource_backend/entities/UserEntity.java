package com.example.freelance_resource_backend.entities;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.UserRole;

@Table(name = "users")
@Data
@Builder
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String userGUID;
	private String email;
	private String password;
	private UserRole role;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}

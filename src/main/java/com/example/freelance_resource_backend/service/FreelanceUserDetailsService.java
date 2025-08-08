package com.example.freelance_resource_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.FreelanceAppUserDetails;
import com.example.freelance_resource_backend.dto.request.user.CreateUserRequest;
import com.example.freelance_resource_backend.dto.response.user.CreateUserResponse;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;
import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.enums.UserRole;
import com.example.freelance_resource_backend.enums.UserStatus;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.UserRepository;
import com.example.freelance_resource_backend.translator.UserTranslator;

@Service
@RequiredArgsConstructor
public class FreelanceUserDetailsService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public CreateUserResponse createUser(CreateUserRequest request) {
		UserEntity userEntity = UserTranslator.toEntity(request);
		String newStudentGUID = UUID.randomUUID().toString();
		LocalDateTime now = LocalDateTime.now();
		userEntity.setUserGUID(newStudentGUID);
		userEntity.setPassword(passwordEncoder.encode(newStudentGUID));
		userEntity.setCreatedDate(now);
		userEntity.setUpdatedDate(now);
		userEntity.setStatus(UserStatus.CREATED);
		userRepository.insertUser(userEntity);
		return UserTranslator.toDto(userEntity);
	}

	public GetUserResponse getUserByUserGUID(String userGUID) throws ResourceNotFoundException {
		Optional<UserEntity> optionalUserEntity = userRepository.getUserByUserGUID(userGUID);
		UserEntity userEntity = optionalUserEntity
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found for GUID: " + userGUID));
		return GetUserResponse.builder()
				.userGUID(userEntity.getUserGUID())
				.email(userEntity.getEmail())
				.name(userEntity.getName())
				.gender(userEntity.getGender())
				.description(userEntity.getDescription())
				.birthday(userEntity.getBirthday())
				.profilePicture(userEntity.getProfilePicture())
				.role(userEntity.getRole())
				.build();
	}

	public GetUserResponse getUserByEmailAndRole(String email, UserRole role) throws ResourceNotFoundException {
		Optional<UserEntity> optionalUserEntity = userRepository.getUserByEmailAndRole(email, role);
		UserEntity userEntity = optionalUserEntity
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found for email: " + email + " and role: " + role));
		return GetUserResponse.builder()
				.userGUID(userEntity.getUserGUID())
				.email(userEntity.getEmail())
				.name(userEntity.getName())
				.build();
	}

	public UserDetails loadUserByEmailAndRole(String email, UserRole role) throws AuthenticationException {
		Optional<UserEntity> optionalUserEntity = userRepository.getUserByEmailAndRole(email, role);
		UserEntity userEntity = optionalUserEntity
				.orElseThrow(() -> new BadCredentialsException("User Not Found for email: " + email));
		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_FREELANCE_APP_USER"));
		return new FreelanceAppUserDetails(
				userEntity.getUserGUID(), userEntity.getEmail(), userEntity.getRole(), userEntity.getPassword(), authorities
		);
	}
}

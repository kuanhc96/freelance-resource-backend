package com.example.freelance_resource_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.enums.UserRole;
import com.example.freelance_resource_backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class FreelanceUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserEntity createUser(String email, String password, UserRole role) {
		String newStudentGUID = UUID.randomUUID().toString();
		LocalDateTime now = LocalDateTime.now();
		UserEntity userEntity = UserEntity.builder()
				.userGUID(newStudentGUID)
				.email(email)
				.password(passwordEncoder.encode(password))
				.role(role)
				.createdDate(now)
				.updatedDate(now)
				.build();
		userRepository.insertUser(userEntity);
		return userEntity;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> optionalUserEntity = userRepository.getUserByEmail(username);
		UserEntity userEntity = optionalUserEntity
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found for email: " + username));
		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().name()));
		return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
	}
}

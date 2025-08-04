package com.example.freelance_resource_backend.translator;

import com.example.freelance_resource_backend.dto.request.user.CreateUserRequest;
import com.example.freelance_resource_backend.dto.response.user.CreateUserResponse;
import com.example.freelance_resource_backend.entities.UserEntity;

public class UserTranslator {
	public static UserEntity toEntity(CreateUserRequest dto) {
		return UserEntity.builder()
				.email(dto.getEmail())
				.role(dto.getRole())
				.status(dto.getStatus())
				.name(dto.getName())
				.gender(dto.getGender())
				.description(dto.getDescription())
				.birthday(dto.getBirthday())
				.profilePicture(dto.getProfilePicture() == null? "favicon.ico": dto.getProfilePicture())
				.build();
	}

	public static CreateUserResponse toDto(UserEntity entity) {
		return CreateUserResponse.builder()
				.userGUID(entity.getUserGUID())
				.email(entity.getEmail())
				.status(entity.getStatus())
				.name(entity.getName())
				.gender(entity.getGender())
				.description(entity.getDescription())
				.birthday(entity.getBirthday())
				.build();
	}
}

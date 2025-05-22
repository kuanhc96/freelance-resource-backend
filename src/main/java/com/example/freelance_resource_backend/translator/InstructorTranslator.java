package com.example.freelance_resource_backend.translator;

import com.example.freelance_resource_backend.dto.request.instructor.CreateInstructorRequest;
import com.example.freelance_resource_backend.dto.response.instructor.GetInstructorResponse;
import com.example.freelance_resource_backend.entities.InstructorEntity;

public class InstructorTranslator {
	public static InstructorEntity toEntity(CreateInstructorRequest request) {
		return InstructorEntity.builder()
				.instructorName(request.getName())
				.email(request.getEmail())
				.birthday(request.getBirthday())
				.gender(request.getGender().getValue())
				.description(request.getDescription())
				.build();
	}

	public static GetInstructorResponse toDto(InstructorEntity entity) {
		return GetInstructorResponse.builder()
				.instructorGUID(entity.getInstructorGUID())
				.email(entity.getEmail())
				.instructorName(entity.getInstructorName())
				.build();
	}
}

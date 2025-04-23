package com.example.freelance_resource_backend.translator;

import com.example.freelance_resource_backend.dto.request.instructor.CreateInstructorRequest;
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
}

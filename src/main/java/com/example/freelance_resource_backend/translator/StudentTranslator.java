package com.example.freelance_resource_backend.translator;

import com.example.freelance_resource_backend.dto.request.student.CreateStudentRequest;
import com.example.freelance_resource_backend.entities.StudentEntity;

public class StudentTranslator {

	public static StudentEntity toEntity(CreateStudentRequest request) {
		return StudentEntity.builder()
				.studentName(request.getName())
				.email(request.getEmail())
				.birthday(request.getBirthday())
				.gender(request.getGender())
				.description(request.getDescription())
				.build();
	}
}

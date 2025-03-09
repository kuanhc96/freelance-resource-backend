package com.example.freelance_resource_backend.translator;

import com.example.freelance_resource_backend.dto.request.student.CreateStudentRequest;
import com.example.freelance_resource_backend.entities.StudentEntity;

public class StudentTranslator {

	public static StudentEntity toEntity(CreateStudentRequest request) {
		return StudentEntity.builder()
				.studentName(request.getStudentName())
				.email(request.getEmail())
				.birthYear(request.getBirthYear())
				.birthMonth(request.getBirthMonth())
				.birthDay(request.getBirthDay())
				.build();
	}

	public static CreateStudentRequest toDTO(StudentEntity entity) {
		return CreateStudentRequest.builder()
				.email(entity.getEmail())
				.studentName(entity.getStudentName())
				.birthYear(entity.getBirthYear())
				.birthMonth(entity.getBirthMonth())
				.birthDay(entity.getBirthDay())
				.build();
	}
}

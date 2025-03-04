package com.example.freelance_resource_backend.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.CreateStudentRequest;
import com.example.freelance_resource_backend.entities.StudentEntity;
import com.example.freelance_resource_backend.enums.UserStatus;
import com.example.freelance_resource_backend.repository.StudentRepository;
import com.example.freelance_resource_backend.translator.StudentTranslator;

@Service
@RequiredArgsConstructor
public class StudentService {
	private final StudentRepository studentRepository;

	@Transactional
	public StudentEntity createStudent(String studentGUID, CreateStudentRequest request) {
		StudentEntity studentEntity = StudentTranslator.toEntity(request);
		studentEntity.setStudentGUID(studentGUID);
		studentEntity.setStatus(UserStatus.CREATED);
		studentRepository.insertStudent(studentEntity);
		return studentEntity;

	}
}

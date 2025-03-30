package com.example.freelance_resource_backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.student.CreateStudentRequest;
import com.example.freelance_resource_backend.entities.StudentEntity;
import com.example.freelance_resource_backend.enums.UserStatus;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
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
		studentEntity.setGender(request.getGender());
		studentEntity.setDescription(request.getDescription());
		studentRepository.insertStudent(studentEntity);
		return studentEntity;
	}

	public StudentEntity getStudentByStudentGUID(String studentGUID) throws ResourceNotFoundException {
		Optional<StudentEntity> optionalStudent = studentRepository.getStudentByStudentGUID(studentGUID);
		if (optionalStudent.isPresent()) {
			return optionalStudent.get();
		}
		throw new ResourceNotFoundException("Student with studentGUID: " + studentGUID + " not found");
	}

	public StudentEntity getStudentByEmail(String email) throws ResourceNotFoundException {
		Optional<StudentEntity> optionalStudent = studentRepository.getStudentByEmail(email);
		if (optionalStudent.isPresent()) {
			return optionalStudent.get();
		}
		throw new ResourceNotFoundException("Student with email: " + email + " not found");
	}
}

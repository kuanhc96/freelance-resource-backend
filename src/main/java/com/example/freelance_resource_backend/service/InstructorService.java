package com.example.freelance_resource_backend.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.instructor.CreateInstructorRequest;
import com.example.freelance_resource_backend.dto.request.student.CreateStudentRequest;
import com.example.freelance_resource_backend.entities.InstructorEntity;
import com.example.freelance_resource_backend.entities.StudentEntity;
import com.example.freelance_resource_backend.enums.UserStatus;
import com.example.freelance_resource_backend.repository.InstructorRepository;
import com.example.freelance_resource_backend.translator.InstructorTranslator;
import com.example.freelance_resource_backend.translator.StudentTranslator;

@Service
@RequiredArgsConstructor
public class InstructorService {
	private final InstructorRepository instructorRepository;

	@Transactional
	public InstructorEntity createInstructor(String instructorGUID, CreateInstructorRequest request) {
		InstructorEntity instructorEntity = InstructorTranslator.toEntity(request);
		instructorEntity.setInstructorGUID(instructorGUID);
		instructorEntity.setStatus(UserStatus.CREATED);
		instructorRepository.insertInstructor(instructorEntity);
		return instructorEntity;
	}

}

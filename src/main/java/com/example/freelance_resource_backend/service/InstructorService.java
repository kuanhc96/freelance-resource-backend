package com.example.freelance_resource_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.instructor.CreateInstructorRequest;
import com.example.freelance_resource_backend.dto.response.instructor.GetInstructorResponse;
import com.example.freelance_resource_backend.entities.InstructorEntity;
import com.example.freelance_resource_backend.enums.UserStatus;
import com.example.freelance_resource_backend.repository.InstructorRepository;
import com.example.freelance_resource_backend.translator.InstructorTranslator;

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

	public List<String> getSubscribedInstructors(String studentGUID) {
		Optional<List<String>> optionalInstructorGUIDsList = instructorRepository.getDistinctInstructorsByStudentGUID(studentGUID);
		return optionalInstructorGUIDsList.orElseGet(List::of);
	}

	public List<GetInstructorResponse> getAllInstructors() {
		List<InstructorEntity> instructorEntities = instructorRepository.getAllInstructors();
		if (instructorEntities.isEmpty()) {
			return List.of();
		}
		List<GetInstructorResponse> instructorResponses = instructorEntities.stream()
				.map(InstructorTranslator::toDto)
				.toList();
		return instructorResponses;
	}

}

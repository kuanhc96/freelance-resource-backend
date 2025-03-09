package com.example.freelance_resource_backend.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.SubjectEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.SubjectRepository;

@Service
@RequiredArgsConstructor
public class SubjectService {
	private final SubjectRepository subjectRepository;

	public SubjectEntity getSubject(String subjectName, String instructorGUID) throws ResourceNotFoundException {
		Optional<SubjectEntity> optionalSubject = subjectRepository.getSubjectBySubjectNameAndInstructorGUID(subjectName, instructorGUID);
		if (optionalSubject.isPresent()) {
			return optionalSubject.get();
		}

		throw new ResourceNotFoundException("Subject with subjectName: " + subjectName + " and instructorGUID: " + instructorGUID + " not found");
	}

	public void createSubject(String subjectName, String instructorGUID, Integer price) {
		SubjectEntity subjectEntity = SubjectEntity.builder()
				.subjectName(subjectName)
				.instructorGUID(instructorGUID)
				.price(price)
				.build();
		subjectRepository.insertSubject(subjectEntity);
	}

	public void updateSubjectName(String subjectName, String instructorGUID, String newSubjectName) throws ResourceNotFoundException {
		SubjectEntity existingSubjectEntity = getSubject(subjectName, instructorGUID);
		existingSubjectEntity.setSubjectName(newSubjectName);
		subjectRepository.updateSubject(existingSubjectEntity);
	}

	public void updateSubjectPrice(String subjectName, String instructorGUID, Integer subjectPrice) throws ResourceNotFoundException {
		SubjectEntity existingSubjectEntity = getSubject(subjectName, instructorGUID);
		existingSubjectEntity.setPrice(subjectPrice);
		subjectRepository.updateSubject(existingSubjectEntity);
	}
}

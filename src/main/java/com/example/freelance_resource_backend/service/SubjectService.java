package com.example.freelance_resource_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.response.subject.GetSubjectResponse;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;
import com.example.freelance_resource_backend.entities.SubjectEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.SubjectRepository;

@Service
@RequiredArgsConstructor
public class SubjectService {
	private final SubjectRepository subjectRepository;
	private final FreelanceUserDetailsService freelanceUserDetailsService;

	public SubjectEntity getSubject(String subjectName, String instructorGUID) throws ResourceNotFoundException {
		Optional<SubjectEntity> optionalSubject = subjectRepository.getSubjectBySubjectNameAndInstructorGUID(subjectName, instructorGUID);
		if (optionalSubject.isPresent()) {
			return optionalSubject.get();
		}

		throw new ResourceNotFoundException("Subject with subjectName: " + subjectName + " and instructorGUID: " + instructorGUID + " not found");
	}

	public List<GetSubjectResponse> getSubjectsByInstructorGUID(String instructorGUID) throws ResourceNotFoundException {
		GetUserResponse instructor = freelanceUserDetailsService.getUserByUserGUID(instructorGUID);
		List<SubjectEntity> subjects = subjectRepository.getSubjectsByInstructorGUID(instructor.getUserGUID());
		return subjects.stream().map(subject -> GetSubjectResponse.builder()
				.subjectGUID((subject.getSubjectGUID()))
				.subjectName(subject.getSubjectName())
				.instructorName(instructor.getName())
				.price(subject.getPrice())
				.subjectDescription(subject.getSubjectDescription())
				.build()).collect(Collectors.toList());

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

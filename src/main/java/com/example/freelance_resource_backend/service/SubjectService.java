package com.example.freelance_resource_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.response.subject.GetSubjectResponse;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;
import com.example.freelance_resource_backend.entities.PackageEntity;
import com.example.freelance_resource_backend.entities.SubjectEntity;
import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.PackageRepository;
import com.example.freelance_resource_backend.repository.SubjectRepository;
import com.example.freelance_resource_backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class SubjectService {
	private final SubjectRepository subjectRepository;
	private final PackageRepository packageRepository;
	private final UserRepository userRepository;

	public SubjectEntity getSubject(String subjectName, String instructorGUID) throws ResourceNotFoundException {
		Optional<SubjectEntity> optionalSubject = subjectRepository.getSubjectBySubjectNameAndInstructorGUID(subjectName, instructorGUID);
		if (optionalSubject.isPresent()) {
			return optionalSubject.get();
		}

		throw new ResourceNotFoundException("Subject with subjectName: " + subjectName + " and instructorGUID: " + instructorGUID + " not found");
	}

	public List<GetSubjectResponse> getSubjectsByInstructorGUID(String instructorGUID) throws ResourceNotFoundException {
		Optional<UserEntity> optionalInstructor = userRepository.getUserByUserGUID(instructorGUID);
		if (optionalInstructor.isPresent()) {
			UserEntity instructor = optionalInstructor.get();
			List<SubjectEntity> subjects = subjectRepository.getSubjectsByInstructorGUID(instructor.getUserGUID());
			return subjects.stream().map(subject -> GetSubjectResponse.builder()
					.subjectGUID((subject.getSubjectGUID()))
					.subjectName(subject.getSubjectName())
					.instructorName(instructor.getName())
					.price(subject.getPrice())
					.subjectDescription(subject.getSubjectDescription())
					.duration(subject.getDuration())
					.build()).collect(Collectors.toList());
		} else {
			throw new ResourceNotFoundException("Instructor with GUID: " + instructorGUID + " not found");
		}
	}

	public SubjectEntity createSubject(String subjectName, String instructorGUID, Integer price, Integer duration, String subjectDescription) {
		SubjectEntity subjectEntity = SubjectEntity.builder()
				.subjectGUID(java.util.UUID.randomUUID().toString())
				.subjectName(subjectName)
				.instructorGUID(instructorGUID)
				.price(price)
				.duration(duration)
				.subjectDescription(subjectDescription)
				.build();
		PackageEntity packageEntity = PackageEntity.builder()
				.packageGUID(java.util.UUID.randomUUID().toString())
				.subjectGUID(subjectEntity.getSubjectGUID())
				.discountCode("BASIC")
				.numberOfLessons(1)
				.discountRate(1.0)
				.build();
		subjectRepository.insertSubject(subjectEntity);
		packageRepository.insertPackage(packageEntity);
		return subjectEntity;
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

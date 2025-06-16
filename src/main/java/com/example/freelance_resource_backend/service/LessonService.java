package com.example.freelance_resource_backend.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.LessonEntity;
import com.example.freelance_resource_backend.entities.SubjectEntity;
import com.example.freelance_resource_backend.enums.LessonStatus;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.LessonRepository;
import com.example.freelance_resource_backend.repository.SubjectRepository;

@Service
@RequiredArgsConstructor
public class LessonService {
	private final LessonRepository lessonRepository;
	private final SubjectRepository subjectRepository;

	public LessonEntity createLesson(String studentGUID, String instructorGUID, LocalDateTime startDate, String location,
									 String topic, String subject, Integer discount) throws ResourceNotFoundException {
		Optional<SubjectEntity> optionalSubject = subjectRepository.getSubjectBySubjectNameAndInstructorGUID(subject, instructorGUID);
		if (optionalSubject.isPresent()) {
			LessonEntity newLessonEntity = LessonEntity.builder()
					.lessonGUID(UUID.randomUUID().toString())
					.studentGUID(studentGUID)
					.instructorGUID(instructorGUID)
					.startDate(startDate)
					.location(location)
					.topic(topic)
					.lessonStatus(LessonStatus.CREATED)
					.subject(subject)
					.discount(discount)
					.build();
			lessonRepository.insertLesson(newLessonEntity);
			return newLessonEntity;
		} else {
			throw new ResourceNotFoundException("Subject with name: " + subject + " not found for instructorGUID: " + instructorGUID);
		}
	}

	public void updateLessonStudent(String lessonGUID, String studentGUID) {
		LessonEntity existingLessonEntity = lessonRepository.getLessonByLessonGUID(lessonGUID);
		existingLessonEntity.setStudentGUID(studentGUID);
		lessonRepository.updateLessonByLessonGUID(existingLessonEntity);
	}

	public void updateLessonInstructor(String lessonGUID, String instructorGUID) {
		LessonEntity existingLessonEntity = lessonRepository.getLessonByLessonGUID(lessonGUID);
		existingLessonEntity.setInstructorGUID(instructorGUID);
		lessonRepository.updateLessonByLessonGUID(existingLessonEntity);
	}

	public void updateLessonStartDate(String lessonGUID, LocalDateTime startDate) {
		LessonEntity existingLessonEntity = lessonRepository.getLessonByLessonGUID(lessonGUID);
		existingLessonEntity.setStartDate(startDate);
		lessonRepository.updateLessonByLessonGUID(existingLessonEntity);
	}
}

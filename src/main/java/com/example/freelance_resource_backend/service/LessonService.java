package com.example.freelance_resource_backend.service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.response.lesson.GetLessonResponse;
import com.example.freelance_resource_backend.entities.LessonEntity;
import com.example.freelance_resource_backend.entities.SubjectEntity;
import com.example.freelance_resource_backend.enums.LessonFrequency;
import com.example.freelance_resource_backend.enums.LessonStatus;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.LessonRepository;
import com.example.freelance_resource_backend.repository.SubjectRepository;
import com.example.freelance_resource_backend.translator.LessonTranslator;

@Service
@RequiredArgsConstructor
public class LessonService {
	private final LessonRepository lessonRepository;
	private final SubjectRepository subjectRepository;

	public List<LessonEntity> createLessons(
			String studentGUID,
			String instructorGUID,
			LocalDateTime startDate,
			String location,
			String topic,
			String subject,
			Integer discount,
			Integer repeat,
			LessonFrequency lessonFrequency
	) throws ResourceNotFoundException {
		Optional<SubjectEntity> optionalSubject = subjectRepository.getSubjectBySubjectNameAndInstructorGUID(subject, instructorGUID);
		if (optionalSubject.isPresent()) {
			List<LessonEntity> lessonEntities = new LinkedList<>();
			for (int i = 0; i <= repeat; i++) {
				LessonStatus lessonStatus = startDate == null? LessonStatus.CREATED : LessonStatus.SCHEDULED;
				lessonEntities.add(
					LessonEntity.builder()
						.lessonGUID(UUID.randomUUID().toString())
						.studentGUID(studentGUID)
						.instructorGUID(instructorGUID)
						.startDate(startDate)
						.location(location)
						.topic(topic)
						.lessonStatus(lessonStatus)
						.subject(subject)
						.discount(discount)
						.build()
				);
				switch (lessonFrequency) {
					case DAILY -> startDate = startDate.plusDays(1);
					case WEEKLY -> startDate = startDate.plusWeeks(1);
					case MONTHLY -> startDate = startDate.plusMonths(1);
					default -> startDate = null;
				}
			}

			for (LessonEntity newLessonEntity : lessonEntities) {
				lessonRepository.insertLesson(newLessonEntity);
			}
			return lessonEntities;
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

	public List<GetLessonResponse> getLessons(String studentGUID, String instructorGUID) throws ResourceNotFoundException {
		List<LessonEntity> lessonEntities = new LinkedList<>();
		if (StringUtils.isNotBlank(studentGUID) && StringUtils.isNotBlank(instructorGUID)) {
			lessonEntities = lessonRepository.getLessonsByStudentGUIDAndInstructorGUID(studentGUID, instructorGUID);
		} else if (StringUtils.isNotBlank(studentGUID)) {
			lessonEntities = lessonRepository.getLessonsByStudentGUID(studentGUID);
		} else if (StringUtils.isNotBlank(instructorGUID)) {
			lessonEntities = lessonRepository.getLessonsByInstructorGUID(instructorGUID);
		}

		if (lessonEntities.isEmpty()) {
			throw new ResourceNotFoundException("No lessons found for studentGUID: " + studentGUID + " or instructorGUID: " + instructorGUID);
		}

		return lessonEntities.stream().map(LessonTranslator::toDto).collect(Collectors.toList());
	}
}

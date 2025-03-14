package com.example.freelance_resource_backend.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.CourseEntity;
import com.example.freelance_resource_backend.entities.SubjectEntity;
import com.example.freelance_resource_backend.enums.CourseStatus;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.CourseRepository;
import com.example.freelance_resource_backend.repository.SubjectRepository;

@Service
@RequiredArgsConstructor
public class CourseService {
	private final CourseRepository courseRepository;
	private final SubjectRepository subjectRepository;

	public CourseEntity createCourse(String studentGUID, String instructorGUID, LocalDateTime startDate, String location,
							 String topic, String subject, Integer discount) throws ResourceNotFoundException {
		Optional<SubjectEntity> optionalSubject = subjectRepository.getSubjectBySubjectNameAndInstructorGUID(subject, instructorGUID);
		if (optionalSubject.isPresent()) {
			CourseEntity newCourseEntity = CourseEntity.builder()
					.courseGUID(UUID.randomUUID().toString())
					.studentGUID(studentGUID)
					.instructorGUID(instructorGUID)
					.startDate(startDate)
					.location(location)
					.topic(topic)
					.courseStatus(CourseStatus.CREATED)
					.subject(subject)
					.discount(discount)
					.build();
			courseRepository.insertCourse(newCourseEntity);
			return newCourseEntity;
		} else {
			throw new ResourceNotFoundException("Subject with name: " + subject + " not found for instructorGUID: " + instructorGUID);
		}
	}

	public void updateCourseStudent(String courseGUID, String studentGUID) {
		CourseEntity existingCourseEntity = courseRepository.getCourseByCourseGUID(courseGUID);
		existingCourseEntity.setStudentGUID(studentGUID);
		courseRepository.updateCourseByCourseGUID(existingCourseEntity);
	}

	public void updateCourseInstructor(String courseGUID, String instructorGUID) {
		CourseEntity existingCourseEntity = courseRepository.getCourseByCourseGUID(courseGUID);
		existingCourseEntity.setInstructorGUID(instructorGUID);
		courseRepository.updateCourseByCourseGUID(existingCourseEntity);
	}

	public void updateCourseStartDate(String courseGUID, LocalDateTime startDate) {
		CourseEntity existingCourseEntity = courseRepository.getCourseByCourseGUID(courseGUID);
		existingCourseEntity.setStartDate(startDate);
		courseRepository.updateCourseByCourseGUID(existingCourseEntity);
	}
}

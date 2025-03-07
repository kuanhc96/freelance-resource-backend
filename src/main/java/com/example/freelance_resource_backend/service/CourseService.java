package com.example.freelance_resource_backend.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.CourseEntity;
import com.example.freelance_resource_backend.repository.CourseRepository;

@Service
@RequiredArgsConstructor
public class CourseService {
	private final CourseRepository courseRepository;

	public void createCourse(String studentGUID, String instructorGUID, LocalDateTime startDate, String location,
							 String topic, Integer price) {
		CourseEntity newCourseEntity = CourseEntity.builder()
				.courseGUID(UUID.randomUUID().toString())
				.studentGUID(studentGUID)
				.instructorGUID(instructorGUID)
				.startDate(startDate)
				.location(location)
				.topic(topic)
				.price(price)
				.build();
		courseRepository.insertCourse(newCourseEntity);
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

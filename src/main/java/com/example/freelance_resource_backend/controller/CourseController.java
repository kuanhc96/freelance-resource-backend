package com.example.freelance_resource_backend.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.course.CreateCourseRequest;
import com.example.freelance_resource_backend.dto.response.course.CreateCourseResponse;
import com.example.freelance_resource_backend.entities.CourseEntity;
import com.example.freelance_resource_backend.entities.SubjectEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.service.CourseService;
import com.example.freelance_resource_backend.service.SubjectService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {
	private final CourseService courseService;

	@RequestMapping("/createCourse")
	public ResponseEntity<CreateCourseResponse> createCourse(@RequestBody CreateCourseRequest request) throws ResourceNotFoundException {
		String subject = request.getSubject();
		String instructorGUID = request.getInstructorGUID();
		String studentGUID = request.getStudentGUID();
		LocalDateTime startDate = request.getStartDate();
		String location = request.getLocation();
		String topic = request.getTopic();
		Integer discount = request.getDiscount();
		CourseEntity newCourseEntity = courseService.createCourse(studentGUID, instructorGUID, startDate, location, topic, subject, discount);
		CreateCourseResponse courseResponse = CreateCourseResponse.builder()
				.courseGUID(newCourseEntity.getCourseGUID())
				.studentGUID(studentGUID)
				.instructorGUID(instructorGUID)
				.startDate(startDate)
				.location(location)
				.topic(topic)
				.subject(subject)
				.courseStatus(newCourseEntity.getCourseStatus())
				.discount(discount)
				.build();
		return ResponseEntity.ok(courseResponse);
	}
}

package com.example.freelance_resource_backend.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.lesson.CreateLessonRequest;
import com.example.freelance_resource_backend.dto.response.lesson.CreateLessonResponse;
import com.example.freelance_resource_backend.entities.LessonEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.service.LessonService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {
	private final LessonService lessonService;

	@RequestMapping("/createLesson")
	public ResponseEntity<CreateLessonResponse> createLesson(@RequestBody CreateLessonRequest request) throws ResourceNotFoundException {
		String subject = request.getSubject();
		String instructorGUID = request.getInstructorGUID();
		String studentGUID = request.getStudentGUID();
		LocalDateTime startDate = request.getStartDate();
		String location = request.getLocation();
		String topic = request.getTopic();
		Integer discount = request.getDiscount();
		LessonEntity newLessonEntity = lessonService.createLesson(studentGUID, instructorGUID, startDate, location, topic, subject, discount);
		CreateLessonResponse lessonResponse = CreateLessonResponse.builder()
				.lessonGUID(newLessonEntity.getLessonGUID())
				.studentGUID(studentGUID)
				.instructorGUID(instructorGUID)
				.startDate(startDate)
				.location(location)
				.topic(topic)
				.subject(subject)
				.lessonStatus(newLessonEntity.getLessonStatus())
				.discount(discount)
				.build();
		return ResponseEntity.ok(lessonResponse);
	}
}

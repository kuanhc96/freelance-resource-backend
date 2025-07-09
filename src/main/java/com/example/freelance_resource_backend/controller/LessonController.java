package com.example.freelance_resource_backend.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.lesson.CreateLessonRequest;
import com.example.freelance_resource_backend.dto.request.lesson.GetLessonsRequest;
import com.example.freelance_resource_backend.dto.response.lesson.CreateLessonsResponse;
import com.example.freelance_resource_backend.dto.response.lesson.GetLessonResponse;
import com.example.freelance_resource_backend.entities.LessonEntity;
import com.example.freelance_resource_backend.enums.LessonFrequency;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.service.LessonService;
import com.example.freelance_resource_backend.translator.LessonTranslator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {
	private final LessonService lessonService;

	@PostMapping("/createLessons")
	public ResponseEntity<List<GetLessonResponse>> createLessons(@RequestBody CreateLessonRequest request) throws ResourceNotFoundException {
		String subject = request.getSubject();
		String instructorGUID = request.getInstructorGUID();
		String studentGUID = request.getStudentGUID();
		LocalDateTime startDate = request.getStartDate();
		String location = request.getLocation();
		String topic = request.getTopic();
		Integer discount = request.getDiscount();
		Integer repeat = request.getRepeat();
		LessonFrequency lessonFrequency = request.getLessonFrequency();

		List<LessonEntity> newLessonEntities = lessonService.createLessons(studentGUID, instructorGUID, startDate, location, topic, subject, discount, repeat, lessonFrequency);
		return ResponseEntity.ok(newLessonEntities.stream().map(LessonTranslator::toDto).toList());
	}

	@GetMapping
	public ResponseEntity<List<GetLessonResponse>> getLessons(
			@RequestParam(value="studentGUID", required = false) String studentGUID,
			@RequestParam(value="instructorGUID", required = false) String instructorGUID
	) throws ResourceNotFoundException {
		if (StringUtils.isBlank(studentGUID) && StringUtils.isBlank(instructorGUID)) {
			return ResponseEntity.badRequest().build();
		}

		List<GetLessonResponse> lessons = lessonService.getLessons(studentGUID, instructorGUID);
		return ResponseEntity.ok(lessons);
	}
}

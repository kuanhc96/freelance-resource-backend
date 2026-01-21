package com.example.freelance_resource_backend.controller;

import static com.example.freelance_resource_backend.constants.ApplicationConstants.INSTRUCTOR_OR_STUDENT;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.lesson.CreateLessonsRequest;
import com.example.freelance_resource_backend.dto.request.lesson.PrecreateLessonsRequest;
import com.example.freelance_resource_backend.dto.response.lesson.CreateLessonsResponse;
import com.example.freelance_resource_backend.dto.response.lesson.GetLessonResponse;
import com.example.freelance_resource_backend.entities.LessonEntity;
import com.example.freelance_resource_backend.enums.LessonFrequency;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.service.LessonService;
import com.example.freelance_resource_backend.translator.LessonTranslator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lesson")
public class LessonController {
	private final LessonService lessonService;

	@PostMapping("/draft")
	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public ResponseEntity<List<GetLessonResponse>> precreateLessons(@RequestBody PrecreateLessonsRequest request) throws ResourceNotFoundException {
		String subjectGUID = request.getSubjectGUID();
		String instructorGUID = request.getInstructorGUID();
		String studentGUID = request.getStudentGUID();
		LocalDateTime startDate = request.getStartDate();
		String locationGUID = request.getLocationGUID();
		LessonFrequency lessonFrequency = request.getLessonFrequency();
		String packageGUID = request.getPackageGUID();

		List<LessonEntity> lessonEntities = lessonService.precreateLessons(studentGUID, instructorGUID, subjectGUID, packageGUID, startDate, lessonFrequency, locationGUID);
		return ResponseEntity.ok(lessonEntities.stream().map(LessonTranslator::toDto).toList());
	}

	@PostMapping
	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public ResponseEntity<CreateLessonsResponse> createLessons(@RequestBody CreateLessonsRequest request) throws ResourceNotFoundException {
		String subjectGUID = request.getSubjectGUID();
		String instructorGUID = request.getInstructorGUID();
		String studentGUID = request.getStudentGUID();
		String packageGUID = request.getPackageGUID();


		List<LessonEntity> lessonEntities = request.getPrecreatedLessons().stream().map(LessonTranslator::toEntity).toList();
		String transactionGUID = lessonService.createLessons(lessonEntities, studentGUID, instructorGUID, subjectGUID, packageGUID);
		return ResponseEntity.ok(CreateLessonsResponse.builder()
				.transactionGUID(transactionGUID)
				.build()
		);
	}

	@GetMapping
	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public ResponseEntity<List<GetLessonResponse>> getLessons(
			@RequestParam(value="studentGUID", required = false) String studentGUID,
			@RequestParam(value="instructorGUID", required = false) String instructorGUID
	) throws ResourceNotFoundException {
		if (StringUtils.isBlank(studentGUID) && StringUtils.isBlank(instructorGUID)) {
			return ResponseEntity.badRequest().build();
		}

		List<GetLessonResponse> lessons = lessonService.getLessons(studentGUID, instructorGUID);
		lessons.sort(Comparator.comparing(GetLessonResponse::getStartDate));
		return ResponseEntity.ok(lessons);
	}
}

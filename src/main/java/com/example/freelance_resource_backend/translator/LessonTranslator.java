package com.example.freelance_resource_backend.translator;

import com.example.freelance_resource_backend.dto.response.lesson.GetLessonResponse;
import com.example.freelance_resource_backend.entities.LessonEntity;

public class LessonTranslator {
	public static GetLessonResponse toDto(LessonEntity lessonEntity) {
		return GetLessonResponse.builder()
				.lessonGUID(lessonEntity.getLessonGUID())
				.topic(lessonEntity.getTopic())
				.instructorGUID(lessonEntity.getInstructorGUID())
				.instructorComments(lessonEntity.getInstructorComments())
				.studentGUID(lessonEntity.getStudentGUID())
				.lessonRating(lessonEntity.getLessonRating())
				.startDate(lessonEntity.getStartDate())
				.location(lessonEntity.getLocation())
				.subject(lessonEntity.getSubject())
				.lessonStatus(lessonEntity.getLessonStatus())
				.discount(lessonEntity.getDiscount())
				.build();
	}
}

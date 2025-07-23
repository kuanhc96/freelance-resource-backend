package com.example.freelance_resource_backend.translator;

import com.example.freelance_resource_backend.dto.response.lesson.GetLessonResponse;
import com.example.freelance_resource_backend.entities.LessonEntity;

public class LessonTranslator {
	public static GetLessonResponse toDto(LessonEntity lessonEntity) {
		return GetLessonResponse.builder()
				.lessonGUID(lessonEntity.getLessonGUID())
				.topic(lessonEntity.getTopic())
				.instructorGUID(lessonEntity.getInstructorGUID())
				.instructorName(lessonEntity.getInstructorName())
				.instructorComments(lessonEntity.getInstructorComments())
				.studentGUID(lessonEntity.getStudentGUID())
				.studentName(lessonEntity.getStudentName())
				.lessonRating(lessonEntity.getLessonRating())
				.startDate(lessonEntity.getStartDate())
				.location(lessonEntity.getLocation())
				.subjectName(lessonEntity.getSubjectName())
				.endDate(lessonEntity.getStartDate().plusMinutes(lessonEntity.getDuration()))
				.lessonStatus(lessonEntity.getLessonStatus())
				.build();
	}
}

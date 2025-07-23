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
				.endDate(lessonEntity.getEndDate())
				.lessonStatus(lessonEntity.getLessonStatus())
				.build();
	}

	public static LessonEntity toEntity(GetLessonResponse response) {
		return LessonEntity.builder()
				.lessonGUID(response.getLessonGUID())
				.topic(response.getTopic())
				.instructorGUID(response.getInstructorGUID())
				.instructorName(response.getInstructorName())
				.instructorComments(response.getInstructorComments())
				.studentGUID(response.getStudentGUID())
				.studentName(response.getStudentName())
				.lessonRating(response.getLessonRating())
				.startDate(response.getStartDate())
				.location(response.getLocation())
				.subjectName(response.getSubjectName())
				.endDate(response.getEndDate())
				.lessonStatus(response.getLessonStatus())
				.build();
	}
}

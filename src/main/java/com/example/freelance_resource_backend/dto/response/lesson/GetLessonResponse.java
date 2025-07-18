package com.example.freelance_resource_backend.dto.response.lesson;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.LessonRating;
import com.example.freelance_resource_backend.enums.LessonStatus;

@Data
@Builder
public class GetLessonResponse {
	private String lessonGUID;
	private String studentGUID;
	private String studentName;
	private String instructorGUID;
	private String instructorName;
	private LocalDateTime startDate;
	private String location;
	private String topic;
	private String subjectName;
	private LessonStatus lessonStatus;
	private Integer discount;
	private String instructorComments;
	private String studentFeedback;
	private LessonRating lessonRating;
}

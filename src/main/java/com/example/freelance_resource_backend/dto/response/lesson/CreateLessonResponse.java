package com.example.freelance_resource_backend.dto.response.lesson;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.LessonStatus;

@Data
@Builder
public class CreateLessonResponse {
	private String lessonGUID;
	private String studentGUID;
	private String instructorGUID;
	private LocalDateTime startDate;
	private String location;
	private String topic;
	private String subject;
	private LessonStatus lessonStatus;
	private Integer discount;
}

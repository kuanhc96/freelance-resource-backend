package com.example.freelance_resource_backend.dto.request.lesson;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.LessonFrequency;

@Data
@Builder
public class PrecreateLessonsRequest {
	private String studentGUID;
	private String instructorGUID;
	private LocalDateTime startDate;
	private String locationGUID;
	private String subjectGUID;
	private String packageGUID;
	private LessonFrequency lessonFrequency;
}

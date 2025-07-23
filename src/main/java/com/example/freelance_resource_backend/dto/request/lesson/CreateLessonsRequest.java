package com.example.freelance_resource_backend.dto.request.lesson;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.dto.response.lesson.GetLessonResponse;
import com.example.freelance_resource_backend.enums.LessonFrequency;

@Data
@Builder
public class CreateLessonsRequest {
	List<GetLessonResponse> precreatedLessons;
	private String studentGUID;
	private String instructorGUID;
	private String subjectGUID;
	private String packageGUID;
}

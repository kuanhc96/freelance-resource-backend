package com.example.freelance_resource_backend.dto.response.lesson;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateLessonsResponse {
	private List<GetLessonResponse> createdLessons;
}

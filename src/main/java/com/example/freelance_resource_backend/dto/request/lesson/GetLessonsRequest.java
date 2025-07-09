package com.example.freelance_resource_backend.dto.request.lesson;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetLessonsRequest {
	private String studentGUID;
	private String instructorGUID;
}

package com.example.freelance_resource_backend.dto.request.course;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCourseRequest {
	private String studentGUID;
	private String instructorGUID;
	private LocalDateTime startDate;
	private String location;
	private String topic;
	private String subject;
	private Integer discount;
}

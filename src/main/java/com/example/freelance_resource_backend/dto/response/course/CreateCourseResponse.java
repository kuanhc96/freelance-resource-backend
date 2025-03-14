package com.example.freelance_resource_backend.dto.response.course;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.CourseStatus;

@Data
@Builder
public class CreateCourseResponse {
	private String courseGUID;
	private String studentGUID;
	private String instructorGUID;
	private LocalDateTime startDate;
	private String location;
	private String topic;
	private String subject;
	private CourseStatus courseStatus;
	private Integer discount;
}

package com.example.freelance_resource_backend.entities;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.CourseCategory;
import com.example.freelance_resource_backend.enums.CourseRating;
import com.example.freelance_resource_backend.enums.CourseStatus;

@Data
@Builder
public class CourseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long courseId;
	private String courseGUID;
	private String studentGUID;
	private String instructorGUID;
	private LocalDateTime startDate;
	private String location;
	private String topic;
	private String instructorComments;
	private String studentFeedback;
	private CourseCategory courseCategory;
	private CourseStatus courseStatus;
	private CourseRating courseRating;
	private Integer price;

}

package com.example.freelance_resource_backend.entities;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.LessonRating;
import com.example.freelance_resource_backend.enums.LessonStatus;

@Data
@Builder
public class LessonEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long lessonId;
	private String lessonGUID;
	private String studentGUID;
	private String studentName;
	private String instructorGUID;
	private String instructorName;
	private LocalDateTime startDate;
	private String location;
	private String topic;
	private String instructorComments;
	private String subject;
	private String studentFeedback;
	private LessonStatus lessonStatus;
	private LessonRating lessonRating;

}

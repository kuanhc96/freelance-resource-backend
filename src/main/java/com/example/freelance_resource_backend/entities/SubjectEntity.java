package com.example.freelance_resource_backend.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long subjectId;

	private String subjectGUID;

	private String subjectName;

	private String instructorGUID;

	private Integer price;

	private String subjectDescription;

	private Integer duration;
}

package com.example.freelance_resource_backend.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PackageEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long packageId;

	private String packageGUID;

	private String discountCode;

	private String subjectGUID;

	private Integer numberOfLessons;

	private Double discountRate;
}

package com.example.freelance_resource_backend.dto.request.package_;

import lombok.Getter;

@Getter
public class CreatePackageRequest {
	private String subjectGUID;
	private String discountCode;
	private Integer numberOfLessons;
	private Double discountRate;
}

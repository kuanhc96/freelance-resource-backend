package com.example.freelance_resource_backend.dto.response.package_;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatePackageResponse {
	private String packageGUID;
	private String subjectGUID;
	private String discountCode;
	private Integer numberOfLessons;
	private Double discountRate;
}

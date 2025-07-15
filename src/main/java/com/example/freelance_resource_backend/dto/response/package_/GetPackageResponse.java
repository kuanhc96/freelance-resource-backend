package com.example.freelance_resource_backend.dto.response.package_;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetPackageResponse {
	private String packageGUID;
	private String discountCode;
	private String subjectGUID;
	private Integer numberOfLessons;
	private Double discountRate;
}

package com.example.freelance_resource_backend.dto.response.subject;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSubjectResponse {
	private String subjectGUID;
	private String subjectName;
	private String instructorGUID;
	private Integer price;
}

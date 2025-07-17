package com.example.freelance_resource_backend.dto.request.subject;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSubjectRequest {
	private String subjectName;
	private String instructorGUID;
	private Integer price;
	private Integer duration;
	private String subjectDescription;
}

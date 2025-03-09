package com.example.freelance_resource_backend.dto.request.subject;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateSubjectNameRequest {
	private String subjectName;
	private String instructorGUID;
	private String newSubjectName;
}

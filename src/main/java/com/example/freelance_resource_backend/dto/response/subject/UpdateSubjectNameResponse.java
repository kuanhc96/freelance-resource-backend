package com.example.freelance_resource_backend.dto.response.subject;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateSubjectNameResponse {
	private String newSubjectName;
	private String instructorGUID;
}

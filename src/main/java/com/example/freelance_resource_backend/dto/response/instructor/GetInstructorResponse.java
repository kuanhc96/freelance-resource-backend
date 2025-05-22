package com.example.freelance_resource_backend.dto.response.instructor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetInstructorResponse {
	private String instructorGUID;
	private String instructorName;
	private String email;
}

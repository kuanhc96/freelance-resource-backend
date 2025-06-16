package com.example.freelance_resource_backend.dto.response.subject;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetSubjectResponse {
	private String subjectGUID;
	private String subjectName;
	private String instructorName;
	private Integer price;
	private String subjectDescription;

}

package com.example.freelance_resource_backend.dto.request.announcement;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateAnnouncementRequest {
	private String instructorGUID;

	private String title;

	private String announcement;
}

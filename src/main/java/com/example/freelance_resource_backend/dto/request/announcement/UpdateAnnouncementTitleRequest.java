package com.example.freelance_resource_backend.dto.request.announcement;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAnnouncementTitleRequest {
	private String title;
}

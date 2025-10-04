package com.example.freelance_resource_backend.dto.response.announcement;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.AnnouncementStatus;

@Data
@Builder
public class UpdateAnnouncementResponse {
	private String announcementGUID;
	private String title;
	private String announcement;
	private AnnouncementStatus announcementStatus;
}

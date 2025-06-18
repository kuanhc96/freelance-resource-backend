package com.example.freelance_resource_backend.dto.request.announcement;

import lombok.Data;

import com.example.freelance_resource_backend.enums.AnnouncementStatus;

@Data
public class UpdateAnnouncementRequest {
	private String announcementGUID;
	private String title;

	private String announcement;

	private AnnouncementStatus announcementStatus;
}

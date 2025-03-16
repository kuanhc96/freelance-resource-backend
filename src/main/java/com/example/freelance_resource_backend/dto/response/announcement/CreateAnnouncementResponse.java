package com.example.freelance_resource_backend.dto.response.announcement;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.AnnouncementStatus;

@Data
@Builder
public class CreateAnnouncementResponse {
	private String announcementGUID;

	private String title;

	private String announcement;

	private String instructorGUID;

	private LocalDateTime createdDate;

	private AnnouncementStatus announcementStatus;
}

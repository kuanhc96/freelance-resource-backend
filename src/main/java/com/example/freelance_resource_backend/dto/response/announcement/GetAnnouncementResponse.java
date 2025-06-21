package com.example.freelance_resource_backend.dto.response.announcement;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.AnnouncementStatus;

@Data
@Builder
public class GetAnnouncementResponse {
	private String announcementGUID;

	private String title;

	private String announcement;

	private String instructorName;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

	private AnnouncementStatus announcementStatus;
}

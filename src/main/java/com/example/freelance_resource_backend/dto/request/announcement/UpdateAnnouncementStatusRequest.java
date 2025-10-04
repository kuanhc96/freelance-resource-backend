package com.example.freelance_resource_backend.dto.request.announcement;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.AnnouncementStatus;

@Data
@Builder
public class UpdateAnnouncementStatusRequest {
	private AnnouncementStatus announcementStatus;
}

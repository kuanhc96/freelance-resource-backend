package com.example.freelance_resource_backend.entities;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.AnnouncementStatus;

@Data
@Builder
public class AnnouncementEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long announcementId;

	private String announcementGUID;

	private String title;

	private String announcement;

	private String instructorGUID;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

	private AnnouncementStatus announcementStatus;
}

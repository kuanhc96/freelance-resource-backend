package com.example.freelance_resource_backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.announcement.CreateAnnouncementRequest;
import com.example.freelance_resource_backend.dto.response.announcement.CreateAnnouncementResponse;
import com.example.freelance_resource_backend.dto.response.announcement.ReadAnnouncementResponse;
import com.example.freelance_resource_backend.entities.AnnouncementEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.service.AnnouncementService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/announcement")
public class AnnouncementController {
	private final AnnouncementService announcementService;

	@PostMapping("/createAnnouncement")
	public ResponseEntity<CreateAnnouncementResponse> createAnnouncement(@RequestBody CreateAnnouncementRequest request) throws ResourceNotFoundException {
		AnnouncementEntity announcementEntity = announcementService.createAnnouncement(request.getInstructorGUID(), request.getTitle(), request.getAnnouncement());

		return ResponseEntity.ok(CreateAnnouncementResponse.builder()
				.announcementGUID(announcementEntity.getAnnouncementGUID())
				.instructorGUID(announcementEntity.getInstructorGUID())
				.title(announcementEntity.getTitle())
				.announcement(announcementEntity.getAnnouncement())
				.createdDate(announcementEntity.getCreatedDate())
				.announcementStatus(announcementEntity.getAnnouncementStatus())
				.build());
	}

	@GetMapping("/{instructorGUID}")
	public ResponseEntity<List<ReadAnnouncementResponse>> getAnnouncements(@PathVariable String instructorGUID) {
		List<AnnouncementEntity> announcementEntities = announcementService.getAnnouncementsByInstructorGUID(instructorGUID);

		List<ReadAnnouncementResponse> responses = announcementEntities.stream().map(announcementEntity -> ReadAnnouncementResponse.builder()
				.announcementGUID(announcementEntity.getAnnouncementGUID())
				.instructorGUID(announcementEntity.getInstructorGUID())
				.title(announcementEntity.getTitle())
				.announcement(announcementEntity.getAnnouncement())
				.createdDate(announcementEntity.getCreatedDate())
				.announcementStatus(announcementEntity.getAnnouncementStatus())
				.build()).collect(Collectors.toList());

		return ResponseEntity.ok(responses);
	}

}

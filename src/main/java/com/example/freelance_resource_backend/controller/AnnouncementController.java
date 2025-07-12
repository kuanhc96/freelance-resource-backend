package com.example.freelance_resource_backend.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.announcement.CreateAnnouncementRequest;
import com.example.freelance_resource_backend.dto.request.announcement.UpdateAnnouncementRequest;
import com.example.freelance_resource_backend.dto.response.announcement.CreateAnnouncementResponse;
import com.example.freelance_resource_backend.dto.response.announcement.GetAnnouncementResponse;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;
import com.example.freelance_resource_backend.entities.AnnouncementEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.service.AnnouncementService;
import com.example.freelance_resource_backend.service.FreelanceUserDetailsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/announcements")
public class AnnouncementController {
	private final AnnouncementService announcementService;
	private final FreelanceUserDetailsService userService;

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
	public ResponseEntity<List<GetAnnouncementResponse>> getAnnouncements(@PathVariable String instructorGUID) throws ResourceNotFoundException {
		GetUserResponse instructor = userService.getUserByUserGUID(instructorGUID);
		List<AnnouncementEntity> announcementEntities = announcementService.getAnnouncementsByInstructorGUID(instructor.getUserGUID());

		List<GetAnnouncementResponse> responses = announcementEntities.stream().map(announcementEntity -> GetAnnouncementResponse.builder()
				.announcementGUID(announcementEntity.getAnnouncementGUID())
				.instructorName(instructor.getName())
				.title(announcementEntity.getTitle())
				.announcement(announcementEntity.getAnnouncement())
				.createdDate(announcementEntity.getCreatedDate())
				.updatedDate(announcementEntity.getUpdatedDate())
				.announcementStatus(announcementEntity.getAnnouncementStatus())
				.build()).collect(Collectors.toList());

		responses.sort(Comparator.comparing(GetAnnouncementResponse::getUpdatedDate).reversed());

		return ResponseEntity.ok(responses);
	}

	@PutMapping("/update")
	public ResponseEntity<Void> updateAnnouncement(@RequestBody UpdateAnnouncementRequest updateAnnouncementRequest) throws ResourceNotFoundException {
		announcementService.updateAnnouncement(updateAnnouncementRequest);
		return ResponseEntity.ok().build();
	}

}

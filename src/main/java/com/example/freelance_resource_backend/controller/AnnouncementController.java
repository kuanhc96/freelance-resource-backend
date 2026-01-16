package com.example.freelance_resource_backend.controller;

import static com.example.freelance_resource_backend.constants.ApplicationConstants.INSTRUCTOR;
import static com.example.freelance_resource_backend.constants.ApplicationConstants.INSTRUCTOR_OR_STUDENT;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.UserRepository;
import com.example.freelance_resource_backend.service.AnnouncementService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/announcements")
public class AnnouncementController {
	private final AnnouncementService announcementService;
	private final UserRepository userRepository;

	@PostMapping
	@PreAuthorize(INSTRUCTOR)
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
	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public ResponseEntity<List<GetAnnouncementResponse>> getAnnouncements(@PathVariable String instructorGUID) throws ResourceNotFoundException {
		Optional<UserEntity> optionalInstructor = userRepository.getUserByUserGUID(instructorGUID);
		if (optionalInstructor.isPresent()) {
			UserEntity instructor = optionalInstructor.get();
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
		} else {
			throw new ResourceNotFoundException("Instructor not found with GUID: " + instructorGUID);
		}
	}

	@PutMapping
	@PreAuthorize(INSTRUCTOR)
	public ResponseEntity<Void> updateAnnouncement(@RequestBody UpdateAnnouncementRequest updateAnnouncementRequest) throws ResourceNotFoundException {
		announcementService.updateAnnouncement(updateAnnouncementRequest);
		return ResponseEntity.ok().build();
	}

}

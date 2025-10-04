package com.example.freelance_resource_backend.controller;

import static com.example.freelance_resource_backend.constants.ApplicationConstants.INSTRUCTOR;
import static com.example.freelance_resource_backend.constants.ApplicationConstants.INSTRUCTOR_OR_STUDENT;
import static com.example.freelance_resource_backend.constants.ApplicationConstants.INTEGRATION_TEST;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.announcement.CreateAnnouncementRequest;
import com.example.freelance_resource_backend.dto.request.announcement.UpdateAnnouncementContentRequest;
import com.example.freelance_resource_backend.dto.request.announcement.UpdateAnnouncementStatusRequest;
import com.example.freelance_resource_backend.dto.request.announcement.UpdateAnnouncementTitleRequest;
import com.example.freelance_resource_backend.dto.response.announcement.CreateAnnouncementResponse;
import com.example.freelance_resource_backend.dto.response.announcement.GetAnnouncementResponse;
import com.example.freelance_resource_backend.dto.response.announcement.UpdateAnnouncementResponse;
import com.example.freelance_resource_backend.entities.AnnouncementEntity;
import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.UserRepository;
import com.example.freelance_resource_backend.service.AnnouncementService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/announcements")
public class AnnouncementController {
	private final AnnouncementService announcementService;
	private final UserRepository userRepository;

	@PostMapping("/createAnnouncement")
	@PreAuthorize(INSTRUCTOR)
	public ResponseEntity<CreateAnnouncementResponse> createAnnouncement(@RequestBody CreateAnnouncementRequest request) throws ResourceNotFoundException {
		if (StringUtils.isEmpty(request.getTitle())) {
			throw new IllegalArgumentException("Title cannot be null or empty");
		}
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

	@DeleteMapping("/deleteAnnouncement/{announcementGUID}")
	@PreAuthorize(INTEGRATION_TEST)
	public ResponseEntity<Void> deleteAnnouncement(@PathVariable String announcementGUID) throws ResourceNotFoundException {
		announcementService.deleteAnnouncement(announcementGUID);
		return ResponseEntity.noContent().build();
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

	@PutMapping("/updateTitle/{announcementGUID}")
	@PreAuthorize(INSTRUCTOR)
	public ResponseEntity<UpdateAnnouncementResponse> updateAnnouncementTitle(@PathVariable String announcementGUID, @RequestBody UpdateAnnouncementTitleRequest updateAnnouncementTitleRequest) throws ResourceNotFoundException {
		if (StringUtils.isEmpty(announcementGUID)) {
			throw new IllegalArgumentException("announcementGUID cannot be null or empty");
		}
		if (StringUtils.isEmpty(updateAnnouncementTitleRequest.getTitle())) {
			throw new IllegalArgumentException("Title cannot be null or empty");
		}
		UpdateAnnouncementResponse response = announcementService.updateAnnouncementTitle(announcementGUID, updateAnnouncementTitleRequest.getTitle());
		return ResponseEntity.ok(response);
	}

	@PutMapping("/updateContent/{announcementGUID}")
	@PreAuthorize(INSTRUCTOR)
	public ResponseEntity<UpdateAnnouncementResponse> updateAnnouncementContent(@PathVariable String announcementGUID, @RequestBody UpdateAnnouncementContentRequest updateAnnouncementContentRequest) throws ResourceNotFoundException {
		if (StringUtils.isEmpty(announcementGUID)) {
			throw new IllegalArgumentException("announcementGUID cannot be null or empty");
		}
		UpdateAnnouncementResponse response = announcementService.updateAnnouncementContent(announcementGUID, updateAnnouncementContentRequest.getAnnouncement());
		return ResponseEntity.ok(response);
	}

	@PutMapping("/updateStatus/{announcementGUID}")
	@PreAuthorize(INSTRUCTOR)
	public ResponseEntity<UpdateAnnouncementResponse> updateAnnouncementStatus(@PathVariable String announcementGUID, @RequestBody UpdateAnnouncementStatusRequest updateAnnouncementStatusRequest) throws ResourceNotFoundException {
		if (StringUtils.isEmpty(announcementGUID)) {
			throw new IllegalArgumentException("announcementGUID cannot be null or empty");
		}
		UpdateAnnouncementResponse response = announcementService.updateAnnouncementStatus(announcementGUID, updateAnnouncementStatusRequest.getAnnouncementStatus());
		return ResponseEntity.ok(response);
	}

}

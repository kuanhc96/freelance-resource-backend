package com.example.freelance_resource_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.AnnouncementEntity;
import com.example.freelance_resource_backend.entities.InstructorEntity;
import com.example.freelance_resource_backend.enums.AnnouncementStatus;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.AnnouncementRepository;
import com.example.freelance_resource_backend.repository.InstructorRepository;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
	private final AnnouncementRepository announcementRepository;
	private final InstructorRepository instructorRepository;

	public AnnouncementEntity createAnnouncement(String instructorGUID, String title, String announcement) throws ResourceNotFoundException {
		Optional<InstructorEntity> optionalInstructor = instructorRepository.getInstructorByInstructorGUID(instructorGUID);
		if (optionalInstructor.isPresent()) {
			AnnouncementEntity announcementEntity = AnnouncementEntity.builder()
					.announcementGUID(UUID.randomUUID().toString())
					.title(title)
					.announcement(announcement)
					.createdDate(LocalDateTime.now())
					.instructorGUID(instructorGUID)
					.announcementStatus(AnnouncementStatus.ACTIVE)
					.build();
			announcementRepository.insertAnnouncement(announcementEntity);
			return announcementEntity;
		} else {
			throw new ResourceNotFoundException("Instructor not found");
		}
	}

	public List<AnnouncementEntity> getAnnouncementsByInstructorGUID(String instructorGUID) {
		return announcementRepository.getAnnouncementsByInstructorGUID(instructorGUID);
	}
}

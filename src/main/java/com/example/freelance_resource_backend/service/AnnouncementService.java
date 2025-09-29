package com.example.freelance_resource_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.announcement.UpdateAnnouncementRequest;
import com.example.freelance_resource_backend.entities.AnnouncementEntity;
import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.enums.AnnouncementStatus;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.AnnouncementRepository;
import com.example.freelance_resource_backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
	private final AnnouncementRepository announcementRepository;
	private final UserRepository userRepository;

	public AnnouncementEntity createAnnouncement(String instructorGUID, String title, String announcement) throws ResourceNotFoundException {
		Optional<UserEntity> optionalInstructor = userRepository.getUserByUserGUID(instructorGUID);
		if (optionalInstructor.isPresent()) {
			AnnouncementEntity announcementEntity = AnnouncementEntity.builder()
					.announcementGUID(UUID.randomUUID().toString())
					.title(title)
					.announcement(announcement)
					.createdDate(LocalDateTime.now())
					.updatedDate(LocalDateTime.now())
					.instructorGUID(instructorGUID)
					.announcementStatus(AnnouncementStatus.ACTIVE)
					.build();
			announcementRepository.insertAnnouncement(announcementEntity);
			return announcementEntity;
		} else {
			throw new ResourceNotFoundException("Instructor not found");
		}
	}

	public void deleteAnnouncement(String announcementGUID) throws ResourceNotFoundException {
		Optional<AnnouncementEntity> optionalAnnouncement = announcementRepository.getAnnouncementByAnnouncementGUID(announcementGUID);
		if (optionalAnnouncement.isPresent()) {
			announcementRepository.deleteAnnouncementByAnnouncementGUID(announcementGUID);
		} else {
			throw new ResourceNotFoundException("Announcement with announcementGUID=" + announcementGUID + " not found");
		}
	}

	public List<AnnouncementEntity> getAnnouncementsByInstructorGUID(String instructorGUID) {
		return announcementRepository.getAnnouncementsByInstructorGUID(instructorGUID);
	}

	public void updateAnnouncement(UpdateAnnouncementRequest updateAnnouncementRequest) throws ResourceNotFoundException {
		String announcementGUID = updateAnnouncementRequest.getAnnouncementGUID();
		Optional<AnnouncementEntity> optionalAnnouncement = announcementRepository.getAnnouncementByAnnouncementGUID(announcementGUID);
		if (optionalAnnouncement.isPresent()) {
			AnnouncementEntity announcementEntity = optionalAnnouncement.get();
			announcementEntity.setTitle(updateAnnouncementRequest.getTitle());
			announcementEntity.setAnnouncement(updateAnnouncementRequest.getAnnouncement());
			announcementEntity.setAnnouncementStatus(updateAnnouncementRequest.getAnnouncementStatus());
			announcementEntity.setUpdatedDate(LocalDateTime.now());
			announcementRepository.updateAnnouncement(announcementEntity);
		} else {
			throw new ResourceNotFoundException("Announcement not found");
		}
	}
}

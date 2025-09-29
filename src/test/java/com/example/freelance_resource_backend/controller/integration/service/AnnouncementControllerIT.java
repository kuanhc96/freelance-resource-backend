package com.example.freelance_resource_backend.controller.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static com.example.freelance_resource_backend.controller.integration.config.TestConfig.TEST_INSTRUCTOR_GUID;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.ObjectUtils;

import com.example.freelance_resource_backend.controller.integration.config.TestConfig;
import com.example.freelance_resource_backend.controller.integration.helper.APIHelper;
import com.example.freelance_resource_backend.controller.integration.helper.OAuthTokenHelper;
import com.example.freelance_resource_backend.dto.request.announcement.CreateAnnouncementRequest;
import com.example.freelance_resource_backend.dto.response.announcement.CreateAnnouncementResponse;
import com.example.freelance_resource_backend.dto.response.announcement.GetAnnouncementResponse;
import com.example.freelance_resource_backend.enums.AnnouncementStatus;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {TestConfig.class, OAuthTokenHelper.class})
@TestPropertySource("/test-test.properties")
public class AnnouncementControllerIT {
	@Autowired
	private APIHelper helper;

	private String announcementGUID;

	@BeforeEach
	void createAnnouncement() {
		CreateAnnouncementRequest request = new CreateAnnouncementRequest();
		request.setAnnouncement("Test Announcement.");
		request.setTitle("Test Title");
		request.setInstructorGUID(TEST_INSTRUCTOR_GUID);
		CreateAnnouncementResponse response = helper.createAnnouncement(request);
		assertNotNull(response);
		assertNotNull(response.getAnnouncementGUID());
		announcementGUID = response.getAnnouncementGUID();
		assertNotNull(response.getCreatedDate());

		assertEquals(AnnouncementStatus.ACTIVE, response.getAnnouncementStatus());
		assertEquals(response.getAnnouncement(), request.getAnnouncement());
		assertEquals(response.getTitle(), request.getTitle());
		assertEquals(response.getInstructorGUID(), request.getInstructorGUID());
	}

	@Test
	void getAnnouncement_success() {
		List<GetAnnouncementResponse> announcements = helper.getAnnouncements(TEST_INSTRUCTOR_GUID);
		assertFalse(ObjectUtils.isEmpty(announcements));
		assertTrue(announcements.stream().anyMatch(announcement -> announcement.getAnnouncementGUID().equals(announcementGUID)));
	}

	@AfterEach
	void deleteAnnouncement() {
		if (StringUtils.isNotBlank(announcementGUID)) {
			helper.deleteAnnouncement(announcementGUID);
		}
	}
}

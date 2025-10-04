package com.example.freelance_resource_backend.controller.integration.helper;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.announcement.CreateAnnouncementRequest;
import com.example.freelance_resource_backend.dto.request.announcement.UpdateAnnouncementContentRequest;
import com.example.freelance_resource_backend.dto.request.announcement.UpdateAnnouncementStatusRequest;
import com.example.freelance_resource_backend.dto.request.announcement.UpdateAnnouncementTitleRequest;
import com.example.freelance_resource_backend.dto.request.user.GetUserRequest;
import com.example.freelance_resource_backend.dto.response.announcement.CreateAnnouncementResponse;
import com.example.freelance_resource_backend.dto.response.announcement.GetAnnouncementResponse;
import com.example.freelance_resource_backend.dto.response.announcement.UpdateAnnouncementResponse;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;
import com.example.freelance_resource_backend.enums.AnnouncementStatus;
import com.example.freelance_resource_backend.enums.UserRole;

@RequiredArgsConstructor
public class APIHelper {
	private final RestTemplate restTemplate;
	private final HttpEntity<MultiValueMap<String, String>> httpEntity;
	private final String resourceUrl;

	public String testConnection() {
		return restTemplate.exchange(
				resourceUrl + "/user/test",
				HttpMethod.GET,
				httpEntity,
				String.class
		).getBody();

	}

	public GetUserResponse getUserByUserGUID(String userGUID) {
		return restTemplate.exchange(
				resourceUrl + "/user" + "/" + userGUID,
				HttpMethod.GET,
				httpEntity,
				GetUserResponse.class
		).getBody();
	}

	public GetUserResponse getUserByUserEmailAndRole(String email, UserRole userRole) {
		GetUserRequest request = GetUserRequest.builder()
				.email(email)
				.role(userRole)
				.build();
		return restTemplate.exchange(
				resourceUrl + "/user?email=" + email + "&role=" + userRole,
				HttpMethod.GET,
				new HttpEntity<>(request, httpEntity.getHeaders()),
				GetUserResponse.class
		).getBody();
	}

	public CreateAnnouncementResponse createAnnouncement(CreateAnnouncementRequest createAnnouncementRequest) {
		return restTemplate.exchange(
				resourceUrl + "/announcements/createAnnouncement",
				HttpMethod.POST,
				new HttpEntity<>(createAnnouncementRequest, httpEntity.getHeaders()),
				CreateAnnouncementResponse.class
		).getBody();
	}

	public void deleteAnnouncement(String announcementGUID) {
		restTemplate.exchange(
				resourceUrl + "/announcements/deleteAnnouncement/%s".formatted(announcementGUID),
				HttpMethod.DELETE,
				httpEntity,
				Void.class
		);
	}

	public List<GetAnnouncementResponse> getAnnouncements(String instructorGUID) {
		return restTemplate.exchange(
				resourceUrl + "/announcements/%s".formatted(instructorGUID),
				HttpMethod.GET,
				httpEntity,
				new ParameterizedTypeReference<List<GetAnnouncementResponse>>() {}
		).getBody();
	}

	public UpdateAnnouncementResponse updateAnnouncementTitle(String announcementGUID, String title) {
		return restTemplate.exchange(
				resourceUrl + "/announcements/updateTitle/%s".formatted(announcementGUID),
				HttpMethod.PUT,
				new HttpEntity<>(UpdateAnnouncementTitleRequest.builder().title(title).build(), httpEntity.getHeaders()),
				UpdateAnnouncementResponse.class
		).getBody();
	}

	public UpdateAnnouncementResponse updateAnnouncementContent(String announcementGUID, String announcement) {
		return restTemplate.exchange(
				resourceUrl + "/announcements/updateContent/%s".formatted(announcementGUID),
				HttpMethod.PUT,
				new HttpEntity<>(UpdateAnnouncementContentRequest.builder().announcement(announcement).build(), httpEntity.getHeaders()),
				UpdateAnnouncementResponse.class
		).getBody();
	}

	public UpdateAnnouncementResponse updateAnnouncementStatus(String announcementGUID, AnnouncementStatus status) {
		return restTemplate.exchange(
				resourceUrl + "/announcements/updateStatus/%s".formatted(announcementGUID),
				HttpMethod.PUT,
				new HttpEntity<>(UpdateAnnouncementStatusRequest.builder().announcementStatus(status).build(), httpEntity.getHeaders()),
				UpdateAnnouncementResponse.class
		).getBody();
	}
}

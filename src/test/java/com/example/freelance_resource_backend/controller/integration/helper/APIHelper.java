package com.example.freelance_resource_backend.controller.integration.helper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;

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
}

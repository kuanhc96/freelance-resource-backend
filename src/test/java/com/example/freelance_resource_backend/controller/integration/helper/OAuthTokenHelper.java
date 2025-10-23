package com.example.freelance_resource_backend.controller.integration.helper;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
@TestPropertySource("/test-${spring.profiles.active:dev}.properties")
public class OAuthTokenHelper {

	private String accessToken;

	public OAuthTokenHelper(
		@Value("${clientId}") String clientId,
		@Value("${clientSecret}") String clientSecret,
		@Value("${application.authUrl}") String authServerLocation,
		@Value("${application.tokenEndpoint}") String accessTokenUrl,
		@Value("${spring.profiles.active}") String profile
	) {
		if (profile.equals("dev")) {
			accessToken = "";
		} else {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.setAccept(List.of(MediaType.APPLICATION_JSON));
			headers.setBasicAuth(clientId, clientSecret, StandardCharsets.UTF_8);

			MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
			params.add("grant_type", "client_credentials");
			params.add("scope", "INTEGRATION_TEST");

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
			ResponseEntity<Map> response = restTemplate.exchange(
					authServerLocation + accessTokenUrl,
					HttpMethod.POST,
					request,
					Map.class
			);

			if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
				throw new IllegalStateException("Token request failed: " + response.getStatusCode());
			}

			accessToken = response.getBody().get("access_token").toString();
		}
	}

	public HttpHeaders getHeadersWithAccessToken() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.setContentType(MediaType.APPLICATION_JSON);

		return headers;
	}

	public HttpHeaders getHeadersWithoutAccessToken() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return headers;
	}
}

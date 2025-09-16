package com.example.freelance_resource_backend.controller.integration.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.freelance_resource_backend.controller.integration.config.TestConfig;
import com.example.freelance_resource_backend.controller.integration.helper.OAuthTokenHelper;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {TestConfig.class, OAuthTokenHelper.class})
@TestPropertySource("/test-test.properties")
public class UserControllerIT {
	@Autowired
	private OAuthTokenHelper oAuthTokenHelper;

	private RestTemplate restTemplate = new RestTemplate();
	private HttpEntity<MultiValueMap<String, String>> httpEntity;

	@BeforeEach
	void setUp() {
		httpEntity = new HttpEntity<>(oAuthTokenHelper.getHeadersWithAccessToken());
	}

	@Test
	void testTestEndpoint() {
		String result = restTemplate.exchange(
			"http://localhost:8081/user/test",
			HttpMethod.GET,
			httpEntity,
			String.class
		).getBody();
		assertNotNull(result);
	}

	@Test
	void getUserByUserGUID_success() {
		String userGUID = "36946828-6696-4b83-be33-6093f2efef70";
		GetUserResponse user = restTemplate.exchange(
			"http://localhost:8081/user/" + userGUID,
			HttpMethod.GET,
			httpEntity,
			GetUserResponse.class
		).getBody();

		assertNotNull(user);
	}
}

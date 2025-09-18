package com.example.freelance_resource_backend.controller.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static com.example.freelance_resource_backend.controller.integration.config.TestConfig.TEST_INSTRUCTOR_GUID;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

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
import com.example.freelance_resource_backend.controller.integration.helper.APIHelper;
import com.example.freelance_resource_backend.controller.integration.helper.OAuthTokenHelper;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;
import com.example.freelance_resource_backend.enums.Gender;
import com.example.freelance_resource_backend.enums.UserRole;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {TestConfig.class, OAuthTokenHelper.class})
@TestPropertySource("/test-test.properties")
public class UserControllerIT {
	@Autowired
	private APIHelper helper;

	@Test
	void testTestEndpoint() {
		String result = helper.testConnection();
		assertNotNull(result);
	}

	@Test
	void getUserByUserGUID_success() {
		GetUserResponse user = helper.getUserByUserGUID(TEST_INSTRUCTOR_GUID);

		assertNotNull(user);
		assertEquals(TEST_INSTRUCTOR_GUID, user.getUserGUID());
		assertEquals(UserRole.INSTRUCTOR, user.getRole());
		assertEquals("Alice Ho", user.getName());
		assertEquals(Gender.FEMALE, user.getGender());
		assertEquals(LocalDate.of(1996, Month.AUGUST, 5), user.getBirthday());
	}
}

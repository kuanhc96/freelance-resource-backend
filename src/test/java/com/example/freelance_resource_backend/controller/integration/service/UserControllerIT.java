package com.example.freelance_resource_backend.controller.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static com.example.freelance_resource_backend.controller.integration.config.TestConfig.TEST_INSTRUCTOR_EMAIL;
import static com.example.freelance_resource_backend.controller.integration.config.TestConfig.TEST_INSTRUCTOR_GUID;
import static com.example.freelance_resource_backend.controller.integration.config.TestConfig.TEST_STUDENT_EMAIL;
import static com.example.freelance_resource_backend.controller.integration.config.TestConfig.TEST_STUDENT_GUID;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.HttpClientErrorException;

import com.example.freelance_resource_backend.controller.integration.config.TestConfig;
import com.example.freelance_resource_backend.controller.integration.helper.APIHelper;
import com.example.freelance_resource_backend.controller.integration.helper.OAuthTokenHelper;
import com.example.freelance_resource_backend.controller.integration.helper.TestHelper;
import com.example.freelance_resource_backend.dto.request.user.CreateUserRequest;
import com.example.freelance_resource_backend.dto.response.user.CreateUserResponse;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;
import com.example.freelance_resource_backend.enums.Gender;
import com.example.freelance_resource_backend.enums.UserRole;
import com.example.freelance_resource_backend.enums.UserStatus;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {TestConfig.class, OAuthTokenHelper.class})
@TestPropertySource("/test-test.properties")
public class UserControllerIT {
	@Autowired
	@Qualifier("apiHelper")
	private APIHelper helper;

	private TestHelper testHelper;

	@Test
	void testTestEndpoint() {
		String result = helper.testConnection();
		assertNotNull(result);
	}

	@Test
	void getUserByUserGUID_success() {
		GetUserResponse instructor = helper.getUserByUserGUID(TEST_INSTRUCTOR_GUID);
		checkInstructorDetails(instructor);

		GetUserResponse student = helper.getUserByUserGUID(TEST_STUDENT_GUID);
		checkStudentDetails(student);

	}

	@Test
	void getUserByUserGUID_userNotFound_404() {
		HttpClientErrorException ex = assertThrows(HttpClientErrorException.class, () -> helper.getUserByUserGUID(UUID.randomUUID().toString()));
		assertEquals(ex.getStatusCode(), HttpStatus.NOT_FOUND);
		System.out.println(ex.getMessage());
	}

	@Test
	void getUserByUserEmailAndRole_success() {
		GetUserResponse instructor = helper.getUserByUserEmailAndRole(TEST_INSTRUCTOR_EMAIL, UserRole.INSTRUCTOR);
		checkInstructorDetails(instructor);

		GetUserResponse student = helper.getUserByUserEmailAndRole(TEST_STUDENT_EMAIL, UserRole.STUDENT);
		checkStudentDetails(student);
	}

	@Test
	void getUserByUserEmailAndRole_notFound_404() {
		HttpClientErrorException ex = assertThrows(
				HttpClientErrorException.class,
				() -> helper.getUserByUserEmailAndRole("testEmail" + LocalDateTime.now().toString(), UserRole.INSTRUCTOR)
		);
		assertEquals(ex.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	@Nested
	@EnabledIf(expression = "#{environment.getProperty('spring.profiles.active') != 'dev'}", reason = "OAuth tests are disabled in dev profile")
	class OAuthEnabledTests {

		private String createdUserGUID;

		@BeforeEach
		void setup() {
			CreateUserRequest createUserRequest = testHelper.createInstructorRequestStub();
			CreateUserResponse createUserResponse = helper.createUser(createUserRequest);
			assertNotNull(createUserResponse);
			assertTrue(StringUtils.isNotBlank(createUserResponse.getUserGUID()));
			assertEquals(createUserRequest.getEmail(), createUserResponse.getUserGUID());
			assertEquals(createUserRequest.getName(), createUserResponse.getName());
			assertEquals(createUserRequest.getRole(), createUserResponse.getRole());
			assertEquals(createUserRequest.getGender(), createUserResponse.getGender());
			assertEquals(createUserRequest.getBirthday(), createUserResponse.getBirthday());
			assertEquals(UserStatus.CREATED, createUserResponse.getStatus());
			createdUserGUID = createUserResponse.getUserGUID();
		}

		@Test
		void placeholder() {
			assertTrue(true);
		}

		@AfterEach
		void tearDown() {
			if (StringUtils.isNotBlank(createdUserGUID)) {
				helper.deleteUser(createdUserGUID);
			}
		}
	}

	private void checkInstructorDetails(GetUserResponse instructor) {
		assertNotNull(instructor);
		assertEquals(TEST_INSTRUCTOR_GUID, instructor.getUserGUID());
		assertEquals(UserRole.INSTRUCTOR, instructor.getRole());
		assertEquals("Alice Ho", instructor.getName());
		assertEquals(TEST_INSTRUCTOR_EMAIL, instructor.getEmail());
		assertEquals(Gender.FEMALE, instructor.getGender());
		assertEquals(LocalDate.of(1996, Month.AUGUST, 5), instructor.getBirthday());
	}

	private void checkStudentDetails(GetUserResponse student) {
		assertNotNull(student);
		assertEquals(TEST_STUDENT_GUID, student.getUserGUID());
		assertEquals(UserRole.STUDENT, student.getRole());
		assertEquals(TEST_STUDENT_EMAIL, student.getEmail());
		assertEquals("Kuantest", student.getName());
		assertEquals(Gender.MALE, student.getGender());
		assertEquals(LocalDate.of(2025, Month.MAY, 12), student.getBirthday());
	}

}

package com.example.freelance_resource_backend.controller.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static com.example.freelance_resource_backend.controller.integration.config.TestConfig.TEST_INSTRUCTOR_EMAIL;
import static com.example.freelance_resource_backend.controller.integration.config.TestConfig.TEST_INSTRUCTOR_GUID;
import static com.example.freelance_resource_backend.controller.integration.config.TestConfig.TEST_STUDENT_EMAIL;
import static com.example.freelance_resource_backend.controller.integration.config.TestConfig.TEST_STUDENT_GUID;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.HttpClientErrorException;

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

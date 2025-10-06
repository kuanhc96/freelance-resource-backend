package com.example.freelance_resource_backend.controller.integration.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static com.example.freelance_resource_backend.controller.integration.config.TestConfig.TEST_INSTRUCTOR_GUID;
import static com.example.freelance_resource_backend.controller.integration.config.TestConfig.TEST_STUDENT_GUID;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.freelance_resource_backend.controller.integration.config.TestConfig;
import com.example.freelance_resource_backend.controller.integration.helper.APIHelper;
import com.example.freelance_resource_backend.controller.integration.helper.OAuthTokenHelper;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {TestConfig.class, OAuthTokenHelper.class})
@TestPropertySource("/test-test.properties")
public class SubscriptionControllerIT {
	@Autowired
	private APIHelper helper;

	@Test
	void getSubscribedStudentsByInstructorGUID_success() {
		List<GetUserResponse> students = helper.getSubscribedStudents(TEST_INSTRUCTOR_GUID);
		assertTrue(students.stream().anyMatch(student -> student.getUserGUID().equals(TEST_STUDENT_GUID)));
	}

	@Test
	void getInstructorsSubscribedToByStudentGUID_success() {
		List<GetUserResponse> instructors = helper.getInstructorsSubscribedTo(TEST_STUDENT_GUID);
		assertTrue(instructors.stream().anyMatch(instructor -> instructor.getUserGUID().equals(TEST_INSTRUCTOR_GUID)));
	}
}

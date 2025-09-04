package com.example.freelance_resource_backend.controller;

import static com.example.freelance_resource_backend.constants.ApplicationConstants.INSTRUCTOR;
import static com.example.freelance_resource_backend.constants.ApplicationConstants.STUDENT;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.subscription.SubscribeRequest;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;
import com.example.freelance_resource_backend.service.SubscriptionService;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

	private final SubscriptionService subscriptionService;

	@PostMapping
	@PreAuthorize(STUDENT)
	public ResponseEntity<Boolean> subscribe(@RequestBody SubscribeRequest subscribeRequest) {
		// Logic for subscription
		boolean result = subscriptionService.subscribe(subscribeRequest.getStudentGUID(), subscribeRequest.getInstructorGUID());
		return ResponseEntity.ok(result);
	}

	@DeleteMapping
	@PreAuthorize(STUDENT)
	public ResponseEntity<Boolean> unsubscribe(@RequestBody SubscribeRequest unsubscribeRequest) {
		// Logic for subscription
		boolean result = subscriptionService.unsubscribe(unsubscribeRequest.getStudentGUID(), unsubscribeRequest.getInstructorGUID());
		return ResponseEntity.ok(result);
	}

	@PutMapping
	@PreAuthorize(INSTRUCTOR)
	public ResponseEntity<Boolean> confirmSubscription(@RequestBody SubscribeRequest confirmRequest) {
		// Logic for updating subscription
		boolean result = subscriptionService.confirmSubscription(confirmRequest.getStudentGUID(), confirmRequest.getInstructorGUID());
		return ResponseEntity.ok(result);
	}

	@GetMapping("/instructors/{studentGUID}")
	@PreAuthorize(STUDENT)
	public ResponseEntity<List<GetUserResponse>> getInstructorsSubscribedTo(@PathVariable String studentGUID) {
		List<GetUserResponse> instructorsSubscribedTo = subscriptionService.getInstructorsSubscribedTo(studentGUID);
		return ResponseEntity.ok(instructorsSubscribedTo);
	}

	@GetMapping("/unsubscribed/{studentGUID}")
	@PreAuthorize(STUDENT)
	public ResponseEntity<List<GetUserResponse>> getInstructorsNotSubscribedTo(@PathVariable String studentGUID) {
		List<GetUserResponse> instructorsNotSubscribedTo = subscriptionService.getInstructorsNotSubscribedTo(studentGUID);
		return ResponseEntity.ok(instructorsNotSubscribedTo);
	}

	@GetMapping("/students/{instructorGUID}")
	@PreAuthorize(INSTRUCTOR)
	public ResponseEntity<List<GetUserResponse>> getSubscribedStudents(@PathVariable String instructorGUID) {
		List<GetUserResponse> myStudents = subscriptionService.getSubscribedStudentsByInstructorGUID(instructorGUID);
		return ResponseEntity.ok(myStudents);
	}
}

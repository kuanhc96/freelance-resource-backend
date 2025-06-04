package com.example.freelance_resource_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.subscription.SubscribeRequest;
import com.example.freelance_resource_backend.service.SubscriptionService;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

	private final SubscriptionService subscriptionService;

	@PostMapping
	public ResponseEntity<Boolean> subscribe(@RequestBody SubscribeRequest subscribeRequest) {
		// Logic for subscription
		boolean result = subscriptionService.subscribe(subscribeRequest.getStudentGUID(), subscribeRequest.getInstructorGUID());
		return ResponseEntity.ok(result);
	}

	@DeleteMapping
	public ResponseEntity<Boolean> unsubscribe(@RequestBody SubscribeRequest unsubscribeRequest) {
		// Logic for subscription
		boolean result = subscriptionService.unsubscribe(unsubscribeRequest.getStudentGUID(), unsubscribeRequest.getInstructorGUID());
		return ResponseEntity.ok(result);
	}

	@PutMapping
	public ResponseEntity<Boolean> confirmSubscription(@RequestBody SubscribeRequest confirmRequest) {
		// Logic for updating subscription
		boolean result = subscriptionService.confirmSubscription(confirmRequest.getStudentGUID(), confirmRequest.getInstructorGUID());
		return ResponseEntity.ok(result);
	}
}

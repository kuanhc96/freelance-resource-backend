package com.example.freelance_resource_backend.controller;

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
	public boolean subscribe(@RequestBody SubscribeRequest subscribeRequest) {
		// Logic for subscription
		return subscriptionService.subscribe(subscribeRequest.getStudentGUID(), subscribeRequest.getInstructorGUID());
	}

	@DeleteMapping
	public boolean unsubscribe(@RequestBody SubscribeRequest unsubscribeRequest) {
		// Logic for subscription
		return subscriptionService.unsubscribe(unsubscribeRequest.getStudentGUID(), unsubscribeRequest.getInstructorGUID());
	}

	@PutMapping
	public boolean confirmSubscription(@RequestBody SubscribeRequest confirmRequest) {
		// Logic for updating subscription
		return subscriptionService.confirmSubscription(confirmRequest.getStudentGUID(), confirmRequest.getInstructorGUID());
	}
}

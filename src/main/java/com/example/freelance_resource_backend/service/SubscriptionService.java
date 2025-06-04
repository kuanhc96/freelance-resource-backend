package com.example.freelance_resource_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.SubscriptionEntity;
import com.example.freelance_resource_backend.repository.SubscriptionRepository;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
	private final SubscriptionRepository subscriptionRepository;

	public boolean isSubscribed(String studentGUID, String instructorGUID) {
		List<SubscriptionEntity> subscriptionList = subscriptionRepository.getInstructorsSubscribedToByStudentGUID(studentGUID);
		return subscriptionList.stream()
				.anyMatch(subscription -> subscription.getInstructorGUID().equals(instructorGUID));
	}

	public boolean subscribe(String studentGUID, String instructorGUID) {
		if (isSubscribed(studentGUID, instructorGUID)) {
			return false; // Already subscribed
		}
		subscriptionRepository.insertSubscription(studentGUID, instructorGUID);
		return true; // Successfully subscribed

	}

	public boolean unsubscribe(String studentGUID, String instructorGUID) {
		if (!isSubscribed(studentGUID, instructorGUID)) {
			return false; // Already unsubscribed
		}
		subscriptionRepository.deleteSubscription(studentGUID, instructorGUID);
		return true; // Successfully unsubscribed
	}

	public List<SubscriptionEntity> getAllInstructorSubscriptions(String studentGUID) {
		List<SubscriptionEntity> subscriptions = subscriptionRepository.getInstructorsSubscribedToByStudentGUID(studentGUID);
		if (ObjectUtils.isEmpty(subscriptions)) {
			return List.of();
		}
		return subscriptions;
	}

	public List<SubscriptionEntity> getAllStudentFollowers(String instructorGUID) {
		List<SubscriptionEntity> subscriptions = subscriptionRepository.getStudentsByInstructorGUID(instructorGUID);
		if (ObjectUtils.isEmpty(subscriptions)) {
			return List.of();
		}
		return subscriptions;
	}

}

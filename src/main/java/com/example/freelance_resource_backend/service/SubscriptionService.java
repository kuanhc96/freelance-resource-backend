package com.example.freelance_resource_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.response.instructor.GetInstructorResponse;
import com.example.freelance_resource_backend.entities.InstructorEntity;
import com.example.freelance_resource_backend.entities.SubscriptionEntity;
import com.example.freelance_resource_backend.enums.SubscriptionStatus;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.SubscriptionRepository;
import com.example.freelance_resource_backend.translator.InstructorTranslator;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
	private final SubscriptionRepository subscriptionRepository;

	public boolean isSubscribed(String studentGUID, String instructorGUID) {
		Optional<SubscriptionEntity> optionalSubscription = subscriptionRepository.getSubscriptionByStudentAndInstructorGUID(studentGUID, instructorGUID);
		return optionalSubscription.isPresent();
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

	public boolean confirmSubscription(String studentGUID, String instructorGUID) {
		if (!isSubscribed(studentGUID, instructorGUID)) {
			return false; // Not subscribed
		}
		subscriptionRepository.updateSubscriptionStatus(studentGUID, instructorGUID, SubscriptionStatus.CONFIRMED);
		return true; // Successfully confirmed
	}

	public List<GetInstructorResponse> getInstructorsSubscribedTo(String studentGUID) {
		List<InstructorEntity> subscriptions = subscriptionRepository.getInstructorsSubscribedToByStudentGUID(studentGUID);
		if (ObjectUtils.isEmpty(subscriptions)) {
			return List.of();
		}
		return subscriptions.stream()
				.map(InstructorTranslator::toDto)
				.toList();
	}

	public List<GetInstructorResponse> getInstructorsNotSubscribedTo(String studentGUID) {
		List<InstructorEntity> subscriptions = subscriptionRepository.getInstructorsNotSubscribedToByStudentGUID(studentGUID);
		if (ObjectUtils.isEmpty(subscriptions)) {
			return List.of();
		}
		return subscriptions.stream()
				.map(InstructorTranslator::toDto)
				.toList();
	}

	// TODO: implement subscribed students in the future
//	public List<SubscriptionEntity> getAllStudentFollowers(String instructorGUID) {
//		List<SubscriptionEntity> subscriptions = subscriptionRepository.getStudentsByInstructorGUID(instructorGUID);
//		if (ObjectUtils.isEmpty(subscriptions)) {
//			return List.of();
//		}
//		return subscriptions;
//	}

}

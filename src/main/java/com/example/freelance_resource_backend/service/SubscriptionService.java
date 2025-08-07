package com.example.freelance_resource_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;
import com.example.freelance_resource_backend.entities.SubscriptionEntity;
import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.enums.SubscriptionStatus;
import com.example.freelance_resource_backend.repository.SubscriptionRepository;

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

	public List<GetUserResponse> getInstructorsSubscribedTo(String studentGUID) {
		List<UserEntity> subscriptions = subscriptionRepository.getInstructorsSubscribedToByStudentGUID(studentGUID);
		if (ObjectUtils.isEmpty(subscriptions)) {
			return List.of();
		}
		return subscriptions.stream()
				.map(user -> GetUserResponse.builder()
						.userGUID(user.getUserGUID())
						.name(user.getName())
						.email(user.getEmail())
						.role(user.getRole())
						.gender(user.getGender())
						.profilePicture(user.getProfilePicture())
						.build()
				)
				.toList();
	}

	public List<GetUserResponse> getInstructorsNotSubscribedTo(String studentGUID) {
		List<UserEntity> subscriptions = subscriptionRepository.getInstructorsNotSubscribedToByStudentGUID(studentGUID);
		if (ObjectUtils.isEmpty(subscriptions)) {
			return List.of();
		}
		return subscriptions.stream()
				.map(user -> GetUserResponse.builder()
						.userGUID(user.getUserGUID())
						.name(user.getName())
						.email(user.getEmail())
						.role(user.getRole())
						.gender(user.getGender())
						.profilePicture(user.getProfilePicture())
						.build()
				)
				.toList();
	}

	public List<GetUserResponse> getSubscribedStudentsByInstructorGUID(String instructorGUID) {
		List<UserEntity> subscriptions = subscriptionRepository.getSubscribedStudentsByInstructorGUID(instructorGUID);
		if (ObjectUtils.isEmpty(subscriptions)) {
			return List.of();
		}
		return subscriptions.stream()
				.map(user -> GetUserResponse.builder()
						.userGUID(user.getUserGUID())
						.name(user.getName())
						.email(user.getEmail())
						.role(user.getRole())
						.gender(user.getGender())
						.profilePicture(user.getProfilePicture())
						.build()
				)
				.toList();
	}

}

package com.example.freelance_resource_backend.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long subscriptionId;

	private String instructorGUID;

	private String studentGUID;
}

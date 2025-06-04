package com.example.freelance_resource_backend.enums;

import lombok.Getter;

@Getter
public enum SubscriptionStatus {
	REQUESTED("requested"),
	CONFIRMED("confirmed");

	private final String value;

	SubscriptionStatus(String value) {
		this.value = value;
	}

	public static SubscriptionStatus getValue(String value) {
		for (SubscriptionStatus type : SubscriptionStatus.values()) {
			if (type.getValue().equalsIgnoreCase(value)) {
				return type;
			}
		}

		throw new IllegalArgumentException("Unknown enum value: %s. Valid values are [%s]".formatted(value,
				String.join(", ", SubscriptionStatus.values().toString())));
	}

}

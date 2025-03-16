package com.example.freelance_resource_backend.enums;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum AnnouncementStatus {
	ACTIVE("active"),
	ARCHIVED("archived");

	private final String value;

	AnnouncementStatus(String value) {
		this.value = value;
	}

	public static AnnouncementStatus getValue(String value) {
		for (AnnouncementStatus type : AnnouncementStatus.values()) {
			if (type.getValue().equalsIgnoreCase(value)) {
				return type;
			}
		}

		throw new IllegalArgumentException("Unknown enum value: %s. Valid values are [%s]".formatted(value,
				Arrays.stream(AnnouncementStatus.values())
						.map(AnnouncementStatus::getValue)  // Get the lowercase values from the enum
						.reduce((v1, v2) -> v1 + ", " + v2)  // Join the values with a comma separator
						.orElse("")));
	}
}

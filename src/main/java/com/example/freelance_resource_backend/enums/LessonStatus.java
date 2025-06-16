package com.example.freelance_resource_backend.enums;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum LessonStatus {
	CREATED("created"),
	SCHEDULED("scheduled"),
	COMPLETED("completed"),
	CONFIRMED("confirmed");

	private final String value;

	LessonStatus(String value) {
		this.value = value;
	}

	public static LessonStatus getValue(String value) {
		for (LessonStatus type : LessonStatus.values()) {
			if (type.getValue().equalsIgnoreCase(value)) {
				return type;
			}
		}

		throw new IllegalArgumentException("Unknown enum value: %s. Valid values are [%s]".formatted(value,
				Arrays.stream(LessonStatus.values())
						.map(LessonStatus::getValue)  // Get the lowercase values from the enum
						.reduce((v1, v2) -> v1 + ", " + v2)  // Join the values with a comma separator
						.orElse("")));
	}

}

package com.example.freelance_resource_backend.enums;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum LessonFrequency {
	DAILY("daily"),
	WEEKLY("weekly"),
	MONTHLY("monthly"),
	NONE("none");

	private final String value;

	LessonFrequency(String value) {
		this.value = value;
	}

	public static LessonFrequency getValue(String value) {
		for (LessonFrequency type : LessonFrequency.values()) {
			if (type.getValue().equalsIgnoreCase(value)) {
				return type;
			}
		}

		throw new IllegalArgumentException("Unknown enum value: %s. Valid values are [%s]".formatted(value,
				Arrays.stream(LessonFrequency.values())
						.map(LessonFrequency::getValue)  // Get the lowercase values from the enum
						.reduce((v1, v2) -> v1 + ", " + v2)  // Join the values with a comma separator
						.orElse("")));
	}

}

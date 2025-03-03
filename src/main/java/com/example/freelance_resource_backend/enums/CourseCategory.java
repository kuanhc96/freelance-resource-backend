package com.example.freelance_resource_backend.enums;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum CourseCategory {
	GOLF("golf");

	private final String value;

	CourseCategory(String value) {
		this.value = value;
	}

	public static CourseCategory getValue(String value) {
		for (CourseCategory type : CourseCategory.values()) {
			if (type.getValue().equalsIgnoreCase(value)) {
				return type;
			}
		}

		throw new IllegalArgumentException("Unknown enum value: %s. Valid values are [%s]".formatted(value,
				Arrays.stream(CourseCategory.values())
						.map(CourseCategory::getValue)  // Get the lowercase values from the enum
						.reduce((v1, v2) -> v1 + ", " + v2)  // Join the values with a comma separator
						.orElse("")));
	}

}

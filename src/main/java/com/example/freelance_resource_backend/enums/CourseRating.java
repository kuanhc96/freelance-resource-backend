package com.example.freelance_resource_backend.enums;

public enum CourseRating {
	POOR(1),
	FAIR(2),
	GOOD(3),
	EXCELLENT(4),
	UNRATED(0);

	private final int value;

	CourseRating(int value) {
		this.value = value;
	}

	public static CourseRating getValue(int value) {
		for (CourseRating type : CourseRating.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}

		throw new IllegalArgumentException("Unknown enum value: %s. Valid values are [1, 2, 3, 4, 5]".formatted(value));
	}

	public int getValue() {
		return value;
	}
}

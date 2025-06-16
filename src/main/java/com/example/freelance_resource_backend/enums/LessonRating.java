package com.example.freelance_resource_backend.enums;

import lombok.Getter;

@Getter
public enum LessonRating {
	POOR(1),
	FAIR(2),
	GOOD(3),
	EXCELLENT(4),
	UNRATED(0);

	private final int value;

	LessonRating(int value) {
		this.value = value;
	}

	public static LessonRating getValue(int value) {
		for (LessonRating type : LessonRating.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}

		throw new IllegalArgumentException("Unknown enum value: %s. Valid values are [1, 2, 3, 4, 5]".formatted(value));
	}

}

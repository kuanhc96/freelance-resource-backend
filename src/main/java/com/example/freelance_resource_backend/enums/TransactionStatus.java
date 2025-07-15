package com.example.freelance_resource_backend.enums;

import lombok.Getter;

@Getter
public enum TransactionStatus {
	PENDING("pending"),
	COMPLETED("completed"),
	CANCELED("canceled");

	private final String value;

	TransactionStatus(String value) {
		this.value = value;
	}

	public static TransactionStatus getValue(String value) {
		for (TransactionStatus type : TransactionStatus.values()) {
			if (type.getValue().equalsIgnoreCase(value)) {
				return type;
			}
		}

		throw new IllegalArgumentException("Unknown enum value: %s. Valid values are [%s]".formatted(value,
				String.join(", ", TransactionStatus.values().toString())));
	}

}

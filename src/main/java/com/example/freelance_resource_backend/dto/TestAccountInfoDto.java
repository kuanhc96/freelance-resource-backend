package com.example.freelance_resource_backend.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "accounts")
public record TestAccountInfoDto(
		String message,
		Map<String, String> details,
		List<String> support
) {
}

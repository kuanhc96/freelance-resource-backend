package com.example.freelance_resource_backend.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class TestAccountInfoDto {
	private String message;
	private Map<String, String> details;
	private List<String> support;
}

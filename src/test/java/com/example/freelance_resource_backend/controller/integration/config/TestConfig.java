package com.example.freelance_resource_backend.controller.integration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.freelance_resource_backend.controller.integration.helper.APIHelper;
import com.example.freelance_resource_backend.controller.integration.helper.OAuthTokenHelper;

@Configuration
@EnableConfigurationProperties
public class TestConfig {
	public static final String TEST_INSTRUCTOR_GUID = "36946828-6696-4b83-be33-6093f2efef70";
	public static final String TEST_STUDENT_GUID = "3b7adf98-c0b1-4fe5-959f-23ba833c74a0";

	@Value("${application.resourceUrl}")
	private String resourceUrl;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public APIHelper apiHelper(RestTemplate restTemplate, OAuthTokenHelper oAuthTokenHelper) {
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(oAuthTokenHelper.getHeadersWithAccessToken());
		return new APIHelper(restTemplate, httpEntity, resourceUrl);
	}
}

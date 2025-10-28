package com.example.freelance_resource_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.client.AuthServerClient;
import com.example.freelance_resource_backend.dto.request.oauth2.GetAccessTokenRequest;

@Configuration
public class OAuth2FeignConfig {

	@Value("${spring.security.oauth2.client.registration.freelance-resource-backend.client-id}") String clientId;
	@Value("${spring.security.oauth2.client.registration.freelance-resource-backend.client-secret}") String clientSecret;
	@Value("${spring.security.oauth2.client.registration.freelance-resource-backend.scope}") String scope;

	@Bean
	public RequestInterceptor oauth2RequestInterceptor(AuthServerClient authServerClient) {
		return template -> {
			GetAccessTokenRequest getAccessTokenRequest = GetAccessTokenRequest.builder()
					.grant_type("client_credentials")
					.client_id(clientId)
					.client_secret(clientSecret)
					.scope(scope)
					.build();
			AuthServerClient.TokenResponse tokenResponse = authServerClient.getToken(getAccessTokenRequest);
			template.header("Authorization", "Bearer " + tokenResponse.access_token);
		};
	}

}

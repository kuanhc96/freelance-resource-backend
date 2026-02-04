package com.example.freelance_resource_backend.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import feign.Param;

import com.example.freelance_resource_backend.config.AuthServerClientConfig;
import com.example.freelance_resource_backend.dto.request.oauth2.GetAccessTokenRequest;
import com.example.freelance_resource_backend.dto.request.user.CreateUserRequest;

@FeignClient(name = "authserver-client", url="${authserver.location}", configuration = AuthServerClientConfig.class)
public interface AuthServerClient {

	@PostMapping(value = "/oauth2/token", consumes="application/x-www-form-urlencoded")
	TokenResponse getToken(GetAccessTokenRequest getAccessTokenRequest);

	class TokenResponse {
		public String access_token;
		public String token_type;
		public Long expires_in;
		public String scope;
	}
}

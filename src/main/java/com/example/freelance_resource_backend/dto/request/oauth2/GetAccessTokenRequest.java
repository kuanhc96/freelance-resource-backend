package com.example.freelance_resource_backend.dto.request.oauth2;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAccessTokenRequest {
	private String grant_type;
	private String client_id;
	private String client_secret;
	private String scope;
}

package com.example.freelance_resource_backend.dto.response.location;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateLocationResponse {
	private String locationGUID;
}

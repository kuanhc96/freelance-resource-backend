package com.example.freelance_resource_backend.dto.response.location;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetLocationResponse {
	private String locationGUID;
	private String userGUID;
	private String locationName;
	private String country;
	private String city;
	private String street;
	private String zipCode;
}

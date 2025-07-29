package com.example.freelance_resource_backend.dto.request.location;

import lombok.Getter;

@Getter
public class CreateLocationRequest {
	private String locationName;
	private String country;
	private String city;
	private String street;
	private String zipCode;
	private String userGUID;
}

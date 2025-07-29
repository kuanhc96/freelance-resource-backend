package com.example.freelance_resource_backend.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String locationId;

	private String locationGUID;

	private String userGUID;

	private String locationName;

	private String country;

	private String city;

	private String street;

	private String zipCode;
}

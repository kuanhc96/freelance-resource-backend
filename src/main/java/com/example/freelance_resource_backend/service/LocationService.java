package com.example.freelance_resource_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.response.location.GetLocationResponse;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;
import com.example.freelance_resource_backend.entities.LocationEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.LocationRepository;

@Service
@RequiredArgsConstructor
public class LocationService {
	private final LocationRepository locationRepository;
	private final FreelanceUserDetailsService freelanceUserDetailsService;

	public List<GetLocationResponse> getLocationsByUserGUID(String userGUID) throws ResourceNotFoundException {
		GetUserResponse user = freelanceUserDetailsService.getUserByUserGUID(userGUID);
		List<LocationEntity> locations = locationRepository.getLocationsByUserGUID(user.getUserGUID());
		return locations.stream().map(locationEntity -> GetLocationResponse.builder()
				.locationGUID(locationEntity.getLocationGUID())
				.locationName(locationEntity.getLocationName())
				.userGUID(locationEntity.getUserGUID())
				.country(locationEntity.getCountry())
				.city(locationEntity.getCity())
				.street(locationEntity.getStreet())
				.zipCode(locationEntity.getZipCode())
				.build()).collect(Collectors.toList());
	}

	public LocationEntity createLocation(String userGUID, String locationName, String country, String city, String street, String zipCode) throws ResourceNotFoundException {
		GetUserResponse user = freelanceUserDetailsService.getUserByUserGUID(userGUID);
		LocationEntity locationEntity = LocationEntity.builder()
				.locationGUID(java.util.UUID.randomUUID().toString())
				.userGUID(user.getUserGUID())
				.locationName(locationName)
				.country(country)
				.city(city)
				.street(street)
				.zipCode(zipCode)
				.build();
		locationRepository.insertLocation(locationEntity);
		return locationEntity;
	}
}

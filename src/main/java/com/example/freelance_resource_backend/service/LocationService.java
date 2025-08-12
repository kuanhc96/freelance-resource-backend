package com.example.freelance_resource_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.response.location.GetLocationResponse;
import com.example.freelance_resource_backend.dto.response.user.GetUserResponse;
import com.example.freelance_resource_backend.entities.LocationEntity;
import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.LocationRepository;
import com.example.freelance_resource_backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LocationService {
	private final LocationRepository locationRepository;
	private final UserRepository userRepository;

	public List<GetLocationResponse> getLocationsByUserGUID(String userGUID) throws ResourceNotFoundException {
		Optional<UserEntity> optionalUserEntity = userRepository.getUserByUserGUID(userGUID);
		if (optionalUserEntity.isPresent()) {
			UserEntity user = optionalUserEntity.get();
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
		} else {
			throw new ResourceNotFoundException("User with GUID " + userGUID + " not found.");
		}
	}

	public LocationEntity createLocation(String userGUID, String locationName, String country, String city, String street, String zipCode) throws ResourceNotFoundException {
		Optional<UserEntity> optionalUserEntity = userRepository.getUserByUserGUID(userGUID);
		if (optionalUserEntity.isPresent()) {
			UserEntity user = optionalUserEntity.get();
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
		} else {
			throw new ResourceNotFoundException("User with GUID " + userGUID + " not found.");
		}
	}
}

package com.example.freelance_resource_backend.controller;

import static com.example.freelance_resource_backend.constants.ApplicationConstants.INSTRUCTOR_OR_STUDENT;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.location.CreateLocationRequest;
import com.example.freelance_resource_backend.dto.response.location.CreateLocationResponse;
import com.example.freelance_resource_backend.dto.response.location.GetLocationResponse;
import com.example.freelance_resource_backend.entities.LocationEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.service.LocationService;

@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class LocationController {
	private final LocationService locationService;

	@PostMapping
	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public CreateLocationResponse createLocation(@RequestBody CreateLocationRequest request) throws ResourceNotFoundException {
		LocationEntity locationEntity = locationService.createLocation(
				request.getUserGUID(),
				request.getLocationName(),
				request.getCountry(),
				request.getCity(),
				request.getStreet(),
				request.getZipCode()
		);
		return CreateLocationResponse.builder()
				.locationGUID(locationEntity.getLocationGUID())
				.build();
	}

	@GetMapping("/{userGUID}")
	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public List<GetLocationResponse> getLocations(@PathVariable String userGUID) throws ResourceNotFoundException {
		return locationService.getLocationsByUserGUID(userGUID);
	}
}

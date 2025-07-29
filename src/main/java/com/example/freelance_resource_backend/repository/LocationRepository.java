package com.example.freelance_resource_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.LocationEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.mapper.LocationMapper;

@Repository
@RequiredArgsConstructor
public class LocationRepository {
	private final LocationMapper locationMapper;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	private final String getPreferredLocationsByUserGUID =
			"SELECT * FROM locations " +
			"WHERE user_guid = :user_guid";

	private final String getPreferredLocationByLocationGUID =
			"SELECT * FROM locations " +
			"WHERE location_guid = :location_guid";

	private final String insertLocation =
			"INSERT INTO locations (location_guid, user_guid, location_name, country, city, street, zip_code) " +
			"VALUES (:location_guid, :user_guid, :location_name, :country, :city, :street, :zip_code)";

	public List<LocationEntity> getLocationsByUserGUID(String userGUID) {
			return jdbcTemplate.query(
					getPreferredLocationsByUserGUID,
					new MapSqlParameterSource("user_guid", userGUID),
					locationMapper
			);
	}

	public Optional<LocationEntity> getLocationByLocationGUID(String locationGUID) throws ResourceNotFoundException {
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(
					getPreferredLocationByLocationGUID,
					new MapSqlParameterSource("location_guid", locationGUID),
					locationMapper
			));
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Location not found with GUID: " + locationGUID);
		}
	}

	public void insertLocation(LocationEntity location) {
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("location_guid", location.getLocationGUID())
				.addValue("user_guid", location.getUserGUID())
				.addValue("location_name", location.getLocationName())
				.addValue("country", location.getCountry())
				.addValue("city", location.getCity())
				.addValue("street", location.getStreet())
				.addValue("zip_code", location.getZipCode());

		jdbcTemplate.update(insertLocation, params);
	}

}

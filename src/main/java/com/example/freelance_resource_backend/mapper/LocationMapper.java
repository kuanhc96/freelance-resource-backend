package com.example.freelance_resource_backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.freelance_resource_backend.entities.LocationEntity;

@Component
public class LocationMapper implements RowMapper<LocationEntity> {
	public static final String LOCATION_ID = "location_id";
	public static final String LOCATION_GUID = "location_guid";
	public static final String LOCATION_NAME = "location_name";
	public static final String USER_GUID = "user_guid";
	public static final String COUNTRY = "country";
	public static final String CITY = "city";
	public static final String STREET = "street";
	public static final String ZIP_CODE = "zip_code";


	@Override
	public LocationEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return LocationEntity.builder()
				.locationId(rs.getString(LOCATION_ID))
				.locationGUID(rs.getString(LOCATION_GUID))
				.locationName(rs.getString(LOCATION_NAME))
				.userGUID(rs.getString(USER_GUID))
				.country(rs.getString(COUNTRY))
				.city(rs.getString(CITY))
				.street(rs.getString(STREET))
				.zipCode(rs.getString(ZIP_CODE))
				.build();
	}
}

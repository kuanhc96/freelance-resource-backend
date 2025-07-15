package com.example.freelance_resource_backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.freelance_resource_backend.entities.PackageEntity;

@Component
public class PackageMapper implements RowMapper<PackageEntity> {
	public static final String PACKAGE_ID = "package_id";
	public static final String PACKAGE_GUID = "package_guid";
	public static final String DISCOUNT_CODE = "discount_code";
	public static final String SUBJECT_GUID = "subject_guid";
	public static final String NUMBER_OF_LESSONS = "number_of_lessons";
	public static final String DISCOUNT_RATE = "discount_rate";

	@Override
	public PackageEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return PackageEntity.builder()
				.packageId(rs.getLong(PACKAGE_ID))
				.packageGUID(rs.getString(PACKAGE_GUID))
				.discountCode(rs.getString(DISCOUNT_CODE))
				.subjectGUID(rs.getString(SUBJECT_GUID))
				.numberOfLessons(rs.getInt(NUMBER_OF_LESSONS))
				.discountRate(rs.getDouble(DISCOUNT_RATE))
				.build();
	}

}

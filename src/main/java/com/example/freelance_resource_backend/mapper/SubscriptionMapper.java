package com.example.freelance_resource_backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.freelance_resource_backend.entities.SubscriptionEntity;

@Component
public class SubscriptionMapper implements RowMapper<SubscriptionEntity> {
	public static final String SUBSCRIPTION_ID = "subscription_id";
	public static final String STUDENT_GUID = "student_guid";
	public static final String INSTRUCTOR_GUID = "instructor_guid";
	public static final String SUBSCRIPTION_STATUS = "subscription_status";


	@Override
	public SubscriptionEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return SubscriptionEntity.builder()
			.subscriptionId(rs.getLong(SUBSCRIPTION_ID))
			.studentGUID(rs.getString(STUDENT_GUID))
			.instructorGUID(rs.getString(INSTRUCTOR_GUID))
			.subscriptionStatus(rs.getString(SUBSCRIPTION_STATUS))
			.build();
	}
}

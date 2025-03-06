package com.example.freelance_resource_backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;

import com.example.freelance_resource_backend.entities.InstructorEntity;
import com.example.freelance_resource_backend.enums.UserStatus;

@Component
public class InstructorMapper implements RowMapper<InstructorEntity> {
	public static final String INSTRUCTOR_ID = "instructor_id";
	public static final String INSTRUCTOR_GUID = "instructor_guid";
	public static final String INSTRUCTOR_NAME = "instructor_name";
	public static final String EMAIL = "email";
	public static final String REVENUE = "revenue";
	public static final String BIRTH_YEAR = "birth_year";
	public static final String BIRTH_MONTH = "birth_month";
	public static final String BIRTH_DAY = "birth_day";
	public static final String USER_STATUS = "user_status";

	@Override
	public InstructorEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return InstructorEntity.builder()
			.instructorId(rs.getLong(INSTRUCTOR_ID))
			.instructorGUID(rs.getString(INSTRUCTOR_GUID))
			.instructorName(rs.getString(INSTRUCTOR_NAME))
			.email(rs.getString(EMAIL))
			.revenue(rs.getInt(REVENUE))
			.birthYear(rs.getInt(BIRTH_YEAR))
			.birthMonth(rs.getInt(BIRTH_MONTH))
			.birthDay(rs.getInt(BIRTH_DAY))
			.status(UserStatus.getValue(rs.getString(USER_STATUS)))
			.build();

	}
}

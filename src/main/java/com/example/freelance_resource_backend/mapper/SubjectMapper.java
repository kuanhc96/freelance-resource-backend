package com.example.freelance_resource_backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.freelance_resource_backend.entities.SubjectEntity;

@Component
public class SubjectMapper implements RowMapper<SubjectEntity> {
	public static final String SUBJECT_ID = "subject_id";
	public static final String SUBJECT_GUID = "subject_guid";
	public static final String SUBJECT_NAME = "subject_name";
	public static final String INSTRUCTOR_GUID = "instructor_guid";
	public static final String SUBJECT_DESCRIPTION = "subject_description";
	public static final String PRICE = "price";
	public static final String DURATION = "duration";

	@Override
	public SubjectEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return SubjectEntity.builder()
				.subjectId(rs.getLong(SUBJECT_ID))
				.subjectGUID(rs.getString(SUBJECT_GUID))
				.subjectName(rs.getString(SUBJECT_NAME))
				.instructorGUID(rs.getString(INSTRUCTOR_GUID))
				.price(rs.getInt(PRICE))
				.subjectDescription(rs.getString(SUBJECT_DESCRIPTION))
				.duration(rs.getInt(DURATION))
				.build();
	}
}

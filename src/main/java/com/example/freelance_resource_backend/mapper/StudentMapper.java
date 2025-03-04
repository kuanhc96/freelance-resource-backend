package com.example.freelance_resource_backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class StudentMapper implements RowMapper<StudentEntity> {
	public static final String STUDENT_ID = "student_id";
	public static final String STUDENT_GUID = "student_guid";
	public static final String STUDENT_NAME = "student_name";
	public static final String EMAIL = "email";
	public static final String BIRTH_YEAR = "birth_year";
	public static final String BIRTH_MONTH = "birth_month";
	public static final String BIRTH_DAY = "birth_day";
	public static final String STATUS = "status";

	@Override
	public StudentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return StudentEntity.builder()
				.studentId(rs.getLong(STUDENT_ID))
				.studentGUID(rs.getString(STUDENT_GUID))
				.studentName(rs.getString(STUDENT_NAME))
				.email(rs.getString(EMAIL))
				.birthYear(rs.getInt(BIRTH_YEAR))
				.birthMonth(rs.getInt(BIRTH_MONTH))
				.birthDay(rs.getInt(BIRTH_DAY))
				.status(UserStatus.getValue(rs.getString(STATUS)))
				.build();
	}
}

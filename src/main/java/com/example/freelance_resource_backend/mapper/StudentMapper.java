package com.example.freelance_resource_backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.freelance_resource_backend.entities.StudentEntity;
import com.example.freelance_resource_backend.enums.Gender;
import com.example.freelance_resource_backend.enums.UserStatus;


@Component
public class StudentMapper implements RowMapper<StudentEntity> {
	public static final String STUDENT_ID = "student_id";
	public static final String STUDENT_GUID = "student_guid";
	public static final String STUDENT_NAME = "student_name";
	public static final String EMAIL = "email";
	public static final String BIRTHDAY = "birthday";
	public static final String STATUS = "status";
	public static final String GENDER = "gender";
	public static final String DESCRIPTION = "description";

	@Override
	public StudentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return StudentEntity.builder()
				.studentId(rs.getLong(STUDENT_ID))
				.studentGUID(rs.getString(STUDENT_GUID))
				.studentName(rs.getString(STUDENT_NAME))
				.email(rs.getString(EMAIL))
				.birthday(rs.getDate(BIRTHDAY).toLocalDate())
				.status(UserStatus.getValue(rs.getString(STATUS)))
				.gender(Gender.getValue(rs.getString(GENDER)))
				.description(rs.getString(DESCRIPTION))
				.build();
	}
}

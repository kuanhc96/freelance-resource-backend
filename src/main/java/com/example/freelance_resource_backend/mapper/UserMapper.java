package com.example.freelance_resource_backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.enums.Gender;
import com.example.freelance_resource_backend.enums.UserRole;
import com.example.freelance_resource_backend.enums.UserStatus;

@Component
public class UserMapper implements RowMapper<UserEntity> {
	public static final String USER_ID = "user_id";
	public static final String USER_GUID = "user_guid";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String ROLE = "role";
	public static final String STATUS = "status";
	public static final String NAME = "name";
	public static final String GENDER = "gender";
	public static final String DESCRIPTION = "description";
	public static final String BIRTHDAY = "birthday";
	public static final String PROFILE_PICTURE = "profile_picture";
	public static final String CREATED_DATE = "created_date";
	public static final String UPDATED_DATE = "updated_date";
	@Override
	public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return UserEntity.builder()
				.userId(rs.getLong(USER_ID))
				.userGUID(rs.getString(USER_GUID))
				.email(rs.getString(EMAIL))
				.password(rs.getString(PASSWORD))
				.role(UserRole.getValue(rs.getString(ROLE)))
				.status(UserStatus.getValue(rs.getString(STATUS)))
				.name(rs.getString(NAME))
				.gender(Gender.getValue(rs.getString(GENDER)))
				.description(rs.getString(DESCRIPTION))
				.birthday(rs.getDate(BIRTHDAY).toLocalDate())
				.profilePicture(rs.getString(PROFILE_PICTURE))
				.createdDate(rs.getTimestamp(CREATED_DATE).toLocalDateTime())
				.updatedDate(rs.getTimestamp(UPDATED_DATE).toLocalDateTime())
				.build();
	}
}

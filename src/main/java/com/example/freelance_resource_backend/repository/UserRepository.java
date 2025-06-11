package com.example.freelance_resource_backend.repository;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.enums.UserRole;
import com.example.freelance_resource_backend.mapper.UserMapper;

@Repository
@RequiredArgsConstructor
public class UserRepository {
	private final UserMapper userMapper;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	private String getUserByUserGUID = "SELECT * FROM users WHERE user_guid = :user_guid";

	private String getUserByEmailAndRole = "SELECT * FROM users WHERE email = :email AND role = :role";

	private String insertUser = "INSERT INTO users " +
			"(user_guid, email, password, role, status, name, gender, description, birthday, profile_picture," +
			" created_date, updated_date) " +
			"VALUES (:user_guid, :email, :password, :role, :status, :name, :gender, :description, :birthday, :profile_picture," +
			" :created_date, :updated_date)";

	public void insertUser(UserEntity user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(UserMapper.USER_GUID, user.getUserGUID());
		params.addValue(UserMapper.EMAIL, user.getEmail());
		params.addValue(UserMapper.PASSWORD, user.getPassword());
		params.addValue(UserMapper.ROLE, user.getRole().name());
		params.addValue(UserMapper.STATUS, user.getStatus().name());
		params.addValue(UserMapper.NAME, user.getName());
		params.addValue(UserMapper.GENDER, user.getGender().name());
		params.addValue(UserMapper.DESCRIPTION, user.getDescription());
		params.addValue(UserMapper.BIRTHDAY, user.getBirthday());
		params.addValue(UserMapper.PROFILE_PICTURE, user.getProfilePicture());
		params.addValue(UserMapper.CREATED_DATE, user.getCreatedDate());
		params.addValue(UserMapper.UPDATED_DATE, user.getUpdatedDate());
		jdbcTemplate.update(insertUser, params);
	}

	public Optional<UserEntity> getUserByUserGUID(String userGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(UserMapper.USER_GUID, userGUID);
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(getUserByUserGUID, params, userMapper));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public Optional<UserEntity> getUserByEmailAndRole(String email, UserRole role) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(UserMapper.EMAIL, email);
		params.addValue(UserMapper.ROLE, role.getValue());
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(getUserByEmailAndRole, params, userMapper));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

}

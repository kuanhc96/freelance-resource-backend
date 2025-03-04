package com.example.freelance_resource_backend.repository;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.mapper.UserMapper;

@Repository
@RequiredArgsConstructor
public class UserRepository {
	private final UserMapper userMapper;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	private String getUserByUserGUID = "SELECT * FROM users WHERE user_guid = :user_guid";

	private String getUserByEmail = "SELECT * FROM users WHERE email = :email";

	private String insertUser = "INSERT INTO users (user_guid, email, password, role, created_date, updated_date) " +
			"VALUES (:user_guid, :email, :password, :role, :created_date, :updated_date)";

	public void insertUser(UserEntity user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(UserMapper.USER_GUID, user.getUserGUID());
		params.addValue(UserMapper.EMAIL, user.getEmail());
		params.addValue(UserMapper.PASSWORD, user.getPassword());
		params.addValue(UserMapper.ROLE, user.getRole().name());
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

	public Optional<UserEntity> getUserByEmail(String email) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(UserMapper.EMAIL, email);
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(getUserByEmail, params, userMapper));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

}

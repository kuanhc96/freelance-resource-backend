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

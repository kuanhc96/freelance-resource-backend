package com.example.freelance_resource_backend.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.SubscriptionEntity;
import com.example.freelance_resource_backend.entities.UserEntity;
import com.example.freelance_resource_backend.enums.SubscriptionStatus;
import com.example.freelance_resource_backend.mapper.SubscriptionMapper;
import com.example.freelance_resource_backend.mapper.UserMapper;

@Repository
@RequiredArgsConstructor
public class SubscriptionRepository {
	private final SubscriptionMapper subscriptionMapper;
	private final UserMapper userMapper;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	private String getInstructorsSubscribedToByStudentGUID =
			"SELECT users.* " +
					"FROM users users " +
					"JOIN subscriptions subscriptions " +
					"ON subscriptions.instructor_guid = users.user_guid " +
					"WHERE subscriptions.student_guid = :student_guid";
	private String getInstructorsNotSubscribedToByStudentGUID =
			"SELECT users.* " +
					"FROM users users " +
					"LEFT JOIN subscriptions subscriptions " +
					"ON subscriptions.instructor_guid = users.user_guid " +
					"AND (subscriptions.student_guid = :student_guid) " +
					"WHERE users.role = 'INSTRUCTOR' " +
					"AND subscriptions.instructor_guid IS NULL";
	private String getStudentsByInstructorGUID =
			"SELECT users.* " +
					"FROM users users " +
					"JOIN subscriptions subscriptions " +
					"ON subscriptions.student_guid = users.user_guid " +
					"WHERE subscriptions.instructor_guid = :instructor_guid";
	private String getSubscriptionByStudentAndInstructorGUID = "SELECT * FROM subscriptions WHERE student_guid = :student_guid AND instructor_guid = :instructor_guid";
	private String insertSubscription = "INSERT INTO subscriptions (student_guid, instructor_guid, subscription_status) VALUES (:student_guid, :instructor_guid, 'REQUESTED')";
	private String deleteSubscription = "DELETE FROM subscriptions WHERE student_guid = :student_guid AND instructor_guid = :instructor_guid";
	private String updateSubscriptionStatus = "UPDATE subscriptions SET subscription_status = :subscription_status WHERE student_guid = :student_guid AND instructor_guid = :instructor_guid";

	public List<UserEntity> getInstructorsSubscribedToByStudentGUID(String studentGUID) {
		return jdbcTemplate.query(getInstructorsSubscribedToByStudentGUID,
				new MapSqlParameterSource(SubscriptionMapper.STUDENT_GUID, studentGUID),
				userMapper);
	}

	public List<UserEntity> getInstructorsNotSubscribedToByStudentGUID(String studentGUID) {
		return jdbcTemplate.query(getInstructorsNotSubscribedToByStudentGUID,
				new MapSqlParameterSource(SubscriptionMapper.STUDENT_GUID, studentGUID),
				userMapper);
	}

	public List<UserEntity> getStudentsByInstructorGUID(String instructorGUID) {
		return jdbcTemplate.query(getStudentsByInstructorGUID,
				new MapSqlParameterSource(SubscriptionMapper.INSTRUCTOR_GUID, instructorGUID),
				userMapper);
	}

	public Optional<SubscriptionEntity> getSubscriptionByStudentAndInstructorGUID(String studentGUID, String instructorGUID) {
		Map<String, String> params = Map.of(
				SubscriptionMapper.STUDENT_GUID, studentGUID,
				SubscriptionMapper.INSTRUCTOR_GUID, instructorGUID
		);
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(getSubscriptionByStudentAndInstructorGUID, params, subscriptionMapper));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public void insertSubscription(String studentGUID, String instructorGUID) {
		Map<String, Object> params = Map.of(
				SubscriptionMapper.STUDENT_GUID, studentGUID,
				SubscriptionMapper.INSTRUCTOR_GUID, instructorGUID
		);
		jdbcTemplate.update(insertSubscription, params);
	}

	public void deleteSubscription(String studentGUID, String instructorGUID) {
		Map<String, String> params = Map.of(
				SubscriptionMapper.STUDENT_GUID, studentGUID,
				SubscriptionMapper.INSTRUCTOR_GUID, instructorGUID
		);
		jdbcTemplate.update(deleteSubscription, params);
	}

	public void updateSubscriptionStatus(String studentGUID, String instructorGUID, SubscriptionStatus subscriptionStatus) {
		Map<String, String> params = Map.of(
				SubscriptionMapper.STUDENT_GUID, studentGUID,
				SubscriptionMapper.INSTRUCTOR_GUID, instructorGUID,
				SubscriptionMapper.SUBSCRIPTION_STATUS, subscriptionStatus.getValue()
		);
		jdbcTemplate.update(updateSubscriptionStatus, params);
	}
}

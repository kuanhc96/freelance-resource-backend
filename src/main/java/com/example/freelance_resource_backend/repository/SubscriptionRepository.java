package com.example.freelance_resource_backend.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.SubscriptionEntity;
import com.example.freelance_resource_backend.enums.SubscriptionStatus;
import com.example.freelance_resource_backend.mapper.SubscriptionMapper;

@Repository
@RequiredArgsConstructor
public class SubscriptionRepository {
	private final SubscriptionMapper subscriptionMapper;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	private String getInstructorsSubscribedToByStudentGUID = "SELECT * FROM subscriptions WHERE student_guid = :student_guid";
	private String getStudentsByInstructorGUID = "SELECT * FROM subscriptions WHERE instructor_guid = :instructor_guid";
	private String insertSubscription = "INSERT INTO subscriptions (student_guid, instructor_guid, subscription_status) VALUES (:student_guid, :instructor_guid, 'REQUESTED')";
	private String deleteSubscription = "DELETE FROM subscriptions WHERE student_guid = :student_guid AND instructor_guid = :instructor_guid";
	private String updateSubscriptionStatus = "UPDATE subscriptions SET subscription_status = :subscription_status WHERE student_guid = :student_guid AND instructor_guid = :instructor_guid";

	public List<SubscriptionEntity> getInstructorsSubscribedToByStudentGUID(String studentGUID) {
		return jdbcTemplate.query(getInstructorsSubscribedToByStudentGUID,
				new MapSqlParameterSource(SubscriptionMapper.STUDENT_GUID, studentGUID),
				subscriptionMapper);
	}

	public List<SubscriptionEntity> getStudentsByInstructorGUID(String instructorGUID) {
		return jdbcTemplate.query(getStudentsByInstructorGUID,
				new MapSqlParameterSource(SubscriptionMapper.INSTRUCTOR_GUID, instructorGUID),
				subscriptionMapper);
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

package com.example.freelance_resource_backend.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.SubjectEntity;
import com.example.freelance_resource_backend.mapper.SubjectMapper;

@Repository
@RequiredArgsConstructor
public class SubjectRepository {
	private final SubjectMapper subjectMapper;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	public final String insertSubject = "INSERT INTO subjects (subject_guid, subject_name, instructor_guid, price) " +
			"VALUES (:subject_guid, :subject_name, :istructor_guid, :price)";

	public void insertSubject(SubjectEntity subject) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(SubjectMapper.SUBJECT_ID, subject.getSubjectId());
		params.addValue(SubjectMapper.SUBJECT_NAME, subject.getSubjectName());
		params.addValue(SubjectMapper.INSTRUCTOR_GUID, subject.getInstructorGUID());
		params.addValue(SubjectMapper.PRICE, subject.getPrice());
		jdbcTemplate.update(insertSubject, params);
	}

}

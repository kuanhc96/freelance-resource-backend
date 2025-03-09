package com.example.freelance_resource_backend.repository;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
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

	public final String getSubjectBySubjectNameAndInstructorGUID = "SELECT * FROM subjects " +
			"WHERE subject_name = :subject_name AND instructor_guid = :instructor_guid";

	public final String updateSubject = "UPDATE subjects " +
			"SET subject_name = :subject_name, price = :price " +
			"WHERE subject_id = :subject_id";

	public final String insertSubject = "INSERT INTO subjects (subject_name, instructor_guid, price) " +
			"VALUES (:subject_name, :istructor_guid, :price)";

	public Optional<SubjectEntity> getSubjectBySubjectNameAndInstructorGUID(String subjectName, String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(SubjectMapper.SUBJECT_NAME, subjectName);
		params.addValue(SubjectMapper.INSTRUCTOR_GUID, instructorGUID);
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(getSubjectBySubjectNameAndInstructorGUID, params, subjectMapper));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public void insertSubject(SubjectEntity subject) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(SubjectMapper.SUBJECT_NAME, subject.getSubjectName());
		params.addValue(SubjectMapper.INSTRUCTOR_GUID, subject.getInstructorGUID());
		params.addValue(SubjectMapper.PRICE, subject.getPrice());
		jdbcTemplate.update(insertSubject, params);
	}

	public void updateSubject(SubjectEntity subject) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(SubjectMapper.SUBJECT_NAME, subject.getSubjectName());
		params.addValue(SubjectMapper.PRICE, subject.getPrice());
		params.addValue(SubjectMapper.SUBJECT_ID, subject.getSubjectId());
		jdbcTemplate.update(updateSubject, params);
	}

}

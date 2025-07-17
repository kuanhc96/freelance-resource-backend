package com.example.freelance_resource_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.SubjectEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.mapper.SubjectMapper;

@Repository
@RequiredArgsConstructor
public class SubjectRepository {
	private final SubjectMapper subjectMapper;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	public final String getSubjectBySubjectNameAndInstructorGUID = "SELECT * FROM subjects " +
			"WHERE subject_name = :subject_name AND instructor_guid = :instructor_guid";

	public final String getSubjectBySubjectGUID = "SELECT * FROM subjects " +
			"WHERE subject_guid = :subject_guid";

	public final String getSubjectsByInstructorGUID = "SELECT * FROM subjects " +
			"WHERE instructor_guid = :instructor_guid";

	public final String updateSubject = "UPDATE subjects " +
			"SET subject_name = :subject_name, price = :price " +
			"WHERE subject_id = :subject_id";

	public final String insertSubject = "INSERT INTO subjects (subject_guid, subject_name, instructor_guid, price, duration, subject_description) " +
			"VALUES (:subject_guid, :subject_name, :instructor_guid, :price, :duration, :subject_description)";

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

	public Optional<SubjectEntity> getSubjectBySubjectGUID(String subjectGUID) throws ResourceNotFoundException {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(SubjectMapper.SUBJECT_GUID, subjectGUID);
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(getSubjectBySubjectGUID, params, subjectMapper));
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Subject with GUID: " + subjectGUID + " not found");
		}
	}

	public List<SubjectEntity> getSubjectsByInstructorGUID(String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(SubjectMapper.INSTRUCTOR_GUID, instructorGUID);
		try {
			return jdbcTemplate.query(getSubjectsByInstructorGUID, params, subjectMapper);
		} catch (EmptyResultDataAccessException e) {
			return List.of();
		}
	}

	public void insertSubject(SubjectEntity subject) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(SubjectMapper.SUBJECT_GUID, subject.getSubjectGUID());
		params.addValue(SubjectMapper.SUBJECT_NAME, subject.getSubjectName());
		params.addValue(SubjectMapper.INSTRUCTOR_GUID, subject.getInstructorGUID());
		params.addValue(SubjectMapper.PRICE, subject.getPrice());
		params.addValue(SubjectMapper.DURATION, subject.getDuration());
		params.addValue(SubjectMapper.SUBJECT_DESCRIPTION, subject.getSubjectDescription());
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

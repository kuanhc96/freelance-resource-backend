package com.example.freelance_resource_backend.repository;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.InstructorEntity;
import com.example.freelance_resource_backend.mapper.InstructorMapper;
import com.example.freelance_resource_backend.mapper.StudentMapper;

@Repository
@RequiredArgsConstructor
public class InstructorRepository {
	private final InstructorMapper instructorMapper;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	private String getInstructorByInstructorGUID = "SELECT * FROM instructors WHERE instructor_guid = :instructorGUID";

	private String getInstructorByEmail = "SELECT * FROM instructors WHERE email = :email";

	private String insertInstructor = "INSERT INTO instructors " +
			"(student_guid, student_name, email, birth_year, birth_month, birth_day, status) " +
			"VALUES (:student_guid, :student_name, :email, :birth_year, :birth_month, :birth_day, :status)";

	public Optional<InstructorEntity> getInstructorByInstructorGUID(String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(StudentMapper.STUDENT_GUID, instructorGUID);
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(getInstructorByInstructorGUID, params, instructorMapper));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public Optional<InstructorEntity> getInstructorByEmail(String email) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(StudentMapper.EMAIL, email);
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(getInstructorByEmail, params, instructorMapper));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public void insertInstructor(InstructorEntity instructor) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(InstructorMapper.INSTRUCTOR_GUID, instructor.getInstructorId());
		params.addValue(InstructorMapper.INSTRUCTOR_NAME, instructor.getInstructorName());
		params.addValue(InstructorMapper.EMAIL, instructor.getEmail());
		params.addValue(InstructorMapper.REVENUE, instructor.getEmail());
		params.addValue(InstructorMapper.BIRTH_YEAR, instructor.getBirthYear());
		params.addValue(InstructorMapper.BIRTH_MONTH, instructor.getBirthMonth());
		params.addValue(InstructorMapper.BIRTH_DAY, instructor.getBirthDay());
		params.addValue(InstructorMapper.USER_STATUS, instructor.getStatus().name());
		jdbcTemplate.update(insertInstructor, params);
	}
}

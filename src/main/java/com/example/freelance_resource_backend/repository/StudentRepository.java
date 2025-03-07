package com.example.freelance_resource_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.StudentEntity;
import com.example.freelance_resource_backend.mapper.InstructorMapper;
import com.example.freelance_resource_backend.mapper.StudentMapper;

@Repository
@RequiredArgsConstructor
public class StudentRepository {
	private final StudentMapper studentMapper;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	private String getStudentByStudentGUID = "SELECT * FROM students WHERE student_guid = :student_guid";

	private String getStudentByEmail = "SELECT * FROM students WHERE email = :email";

	private String insertStudent = "INSERT INTO students (student_guid, student_name, email, birth_year, birth_month, birth_day, status) " +
			"VALUES (:student_guid, :student_name, :email, :birth_year, :birth_month, :birth_day, :status)";

	private String getDistinctStudentsByInstructorGUID = "SELECT DISTINCT student_guid FROM students " +
			"WHERE instructor_guid = :instructor_guid";

	public Optional<StudentEntity> getStudentByStudentGUID(String studentGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(StudentMapper.STUDENT_GUID, studentGUID);
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(getStudentByStudentGUID, params, studentMapper));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public Optional<StudentEntity> getStudentByEmail(String email) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(StudentMapper.EMAIL, email);
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(getStudentByEmail, params, studentMapper));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public void insertStudent(StudentEntity student) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(StudentMapper.STUDENT_GUID, student.getStudentGUID());
		params.addValue(StudentMapper.STUDENT_NAME, student.getStudentName());
		params.addValue(StudentMapper.EMAIL, student.getEmail());
		params.addValue(StudentMapper.BIRTH_YEAR, student.getBirthYear());
		params.addValue(StudentMapper.BIRTH_MONTH, student.getBirthMonth());
		params.addValue(StudentMapper.BIRTH_DAY, student.getBirthDay());
		params.addValue(StudentMapper.STATUS, student.getStatus().name());
		jdbcTemplate.update(insertStudent, params);
	}

	public Optional<List<String>> getDistinctStudentsByInstructorGUID(String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(InstructorMapper.INSTRUCTOR_GUID, instructorGUID);
		try {
			return Optional.of(jdbcTemplate.queryForList(getDistinctStudentsByInstructorGUID, params, String.class));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

}

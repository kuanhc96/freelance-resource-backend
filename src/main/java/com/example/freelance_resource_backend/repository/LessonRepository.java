package com.example.freelance_resource_backend.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.LessonEntity;
import com.example.freelance_resource_backend.enums.LessonRating;
import com.example.freelance_resource_backend.enums.LessonStatus;
import com.example.freelance_resource_backend.mapper.LessonMapper;

@Repository
@RequiredArgsConstructor
public class LessonRepository {
	private final LessonMapper lessonMapper;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	private String getLessonByLessonGUID = "SELECT * FROM lessons WHERE lesson_guid = :lesson_guid";

	private String getLessonsByStudentGUID = "SELECT i.`name` instructor_name, s.`name` student_name, l.* " +
			"FROM lessons l " +
			"JOIN users i " +
			"ON l.instructor_guid = i.user_guid " +
			"JOIN users s " +
			"ON l.instructor_guid = s.user_guid " +
			"WHERE student_guid = :student_guid";

	private String getLessonsByInstructorGUID = "SELECT s.`name` student_name, i.`name` instructor_name, l.* " +
			"FROM lessons l " +
			"JOIN users s " +
			"ON l.student_guid = s.user_guid " +
			"JOIN users i " +
			"ON l.student_guid = i.user_guid " +
			"WHERE instructor_guid = :instructor_guid";

	private String getLessonsByStudentGUIDAndInstructorGUID = "SELECT * FROM lesson WHERE student_guid = :student_guid AND instructor_guid = :instructor_guid";

	private String insertLesson = "INSERT INTO lessons (lesson_guid, student_guid, instructor_guid, start_date, location, topic, instructor_comments, student_feedback, subject_guid, lesson_status, lesson_rating, transaction_guid) " +
			"VALUES (:lesson_guid, :student_guid, :instructor_guid, :start_date, :location, :topic, :instructor_comments, :student_feedback, :subject_guid, :lesson_status, :lesson_rating, :transaction_guid)";

	private String updateLessonByLessonGUID = "UPDATE lesson " +
			"SET student_guid = :studentGUID, instructor_guid = :instructorGUID, start_date = :startDate, location = :location, " +
				"topic = :topic, instructor_comments = :instructorComments, student_feedback = :studentFeedback, subject_guid = :subject_guid, " +
				"lesson_status = :lessonStatus, lesson_rating = :lessonRating " +
			"WHERE lesson_guid = :lessonGUID";

	public LessonEntity getLessonByLessonGUID(String lessonGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(LessonMapper.LESSON_GUID, lessonGUID);
		return jdbcTemplate.queryForObject(getLessonByLessonGUID, params, lessonMapper);
	}

	public List<LessonEntity> getLessonsByStudentGUID(String studentGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(LessonMapper.STUDENT_GUID, studentGUID);
		return jdbcTemplate.query(getLessonsByStudentGUID, params, lessonMapper);
	}

	public List<LessonEntity> getLessonsByInstructorGUID(String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(LessonMapper.INSTRUCTOR_GUID, instructorGUID);
		return jdbcTemplate.query(getLessonsByInstructorGUID, params, lessonMapper);
	}

	public List<LessonEntity> getLessonsByStudentGUIDAndInstructorGUID(String studentGUID, String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(LessonMapper.STUDENT_GUID, studentGUID);
		params.addValue(LessonMapper.INSTRUCTOR_GUID, instructorGUID);
		return jdbcTemplate.query(getLessonsByStudentGUIDAndInstructorGUID, params, lessonMapper);
	}

	public void insertLesson(LessonEntity lesson) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(LessonMapper.LESSON_GUID, lesson.getLessonGUID());
		params.addValue(LessonMapper.STUDENT_GUID, lesson.getStudentGUID());
		params.addValue(LessonMapper.INSTRUCTOR_GUID, lesson.getInstructorGUID());
		params.addValue(LessonMapper.START_DATE, lesson.getStartDate());
		params.addValue(LessonMapper.LOCATION, lesson.getLocation());
		params.addValue(LessonMapper.TOPIC, lesson.getTopic());
		params.addValue(LessonMapper.INSTRUCTOR_COMMENTS, lesson.getInstructorComments());
		params.addValue(LessonMapper.STUDENT_FEEDBACK, lesson.getStudentFeedback());
		params.addValue(LessonMapper.LESSON_STATUS, lesson.getLessonStatus() == null? LessonStatus.CREATED.getValue(): lesson.getLessonStatus().getValue());
		params.addValue(LessonMapper.LESSON_RATING, lesson.getLessonRating() == null? LessonRating.UNRATED.getValue(): lesson.getLessonRating().getValue());
		params.addValue(LessonMapper.SUBJECT_GUID, lesson.getSubjectGUID());
		params.addValue(LessonMapper.TRANSACTION_GUID, lesson.getTransactionGUID());
		jdbcTemplate.update(insertLesson, params);
	}

	public void updateLessonByLessonGUID(LessonEntity lesson) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(LessonMapper.LESSON_GUID, lesson.getLessonGUID());
		params.addValue(LessonMapper.STUDENT_GUID, lesson.getStudentGUID());
		params.addValue(LessonMapper.INSTRUCTOR_GUID, lesson.getInstructorGUID());
		params.addValue(LessonMapper.START_DATE, lesson.getStartDate());
		params.addValue(LessonMapper.LOCATION, lesson.getLocation());
		params.addValue(LessonMapper.TOPIC, lesson.getTopic());
		params.addValue(LessonMapper.INSTRUCTOR_COMMENTS, lesson.getInstructorComments());
		params.addValue(LessonMapper.STUDENT_FEEDBACK, lesson.getStudentFeedback());
		params.addValue(LessonMapper.LESSON_STATUS, lesson.getLessonStatus().name());
		params.addValue(LessonMapper.LESSON_RATING, lesson.getLessonRating().name());
		params.addValue(LessonMapper.SUBJECT_GUID, lesson.getSubjectGUID());
		jdbcTemplate.update(updateLessonByLessonGUID, params);
	}

}

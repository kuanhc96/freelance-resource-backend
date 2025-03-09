package com.example.freelance_resource_backend.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.CourseEntity;
import com.example.freelance_resource_backend.mapper.CourseMapper;
import com.example.freelance_resource_backend.mapper.StudentMapper;

@Repository
@RequiredArgsConstructor
public class CourseRepository {
	private final CourseMapper courseMapper;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	private String getCourseByCourseGUID = "SELECT * FROM course WHERE course_guid = :courseGUID";
	private String getCoursesByStudentGUID = "SELECT * FROM course WHERE student_guid = :studentGUID";
	private String getCoursesByInstructorGUID = "SELECT * FROM course WHERE instructor_guid = :instructorGUID";
	private String getCoursesByStudentGUIDAndInstructorGUID = "SELECT * FROM course WHERE student_guid = :studentGUID AND instructor_guid = :instructorGUID";

	private String insertCourse = "INSERT INTO course (course_guid, student_guid, instructor_guid, start_date, location, topic, instructor_comments, student_feedback, subject, course_status, course_rating, discount) " +
			"VALUES (:courseGUID, :studentGUID, :instructorGUID, :startDate, :location, :topic, :instructorComments, :studentFeedback, :subject, :courseStatus, :courseRating, :discount)";

	private String updateCourseByCourseGUID = "UPDATE course " +
			"SET student_guid = :studentGUID, instructor_guid = :instructorGUID, start_date = :startDate, location = :location, " +
				"topic = :topic, instructor_comments = :instructorComments, student_feedback = :studentFeedback, subject = :subject, " +
				"course_status = :courseStatus, course_rating = :courseRating, discount = :discount " +
			"WHERE course_guid = :courseGUID";

	public CourseEntity getCourseByCourseGUID(String courseGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(CourseMapper.COURSE_GUID, courseGUID);
		return jdbcTemplate.queryForObject(getCourseByCourseGUID, params, courseMapper);
	}

	public List<CourseEntity> getCoursesByStudentGUID(String studentGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(CourseMapper.STUDENT_GUID, studentGUID);
		return jdbcTemplate.query(getCoursesByStudentGUID, params, courseMapper);
	}

	public List<CourseEntity> getCoursesByInstructorGUID(String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(CourseMapper.INSTRUCTOR_GUID, instructorGUID);
		return jdbcTemplate.query(getCoursesByInstructorGUID, params, courseMapper);
	}

	public List<CourseEntity> getCoursesByStudentGUIDAndInstructorGUID(String studentGUID, String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(CourseMapper.STUDENT_GUID, studentGUID);
		params.addValue(CourseMapper.INSTRUCTOR_GUID, instructorGUID);
		return jdbcTemplate.query(getCoursesByStudentGUIDAndInstructorGUID, params, courseMapper);
	}

	public void insertCourse(CourseEntity course) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(CourseMapper.COURSE_GUID, course.getCourseGUID());
		params.addValue(CourseMapper.STUDENT_GUID, course.getStudentGUID());
		params.addValue(CourseMapper.INSTRUCTOR_GUID, course.getInstructorGUID());
		params.addValue(CourseMapper.START_DATE, course.getStartDate());
		params.addValue(CourseMapper.LOCATION, course.getLocation());
		params.addValue(CourseMapper.TOPIC, course.getTopic());
		params.addValue(CourseMapper.INSTRUCTOR_COMMENTS, course.getInstructorComments());
		params.addValue(CourseMapper.STUDENT_FEEDBACK, course.getStudentFeedback());
		params.addValue(CourseMapper.COURSE_STATUS, course.getCourseStatus().name());
		params.addValue(CourseMapper.COURSE_RATING, course.getCourseRating().name());
		params.addValue(CourseMapper.DISCOUNT, course.getDiscount());
		params.addValue(CourseMapper.SUBJECT, course.getSubject());
		jdbcTemplate.update(insertCourse, params);
	}

	public void updateCourseByCourseGUID(CourseEntity course) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(CourseMapper.COURSE_GUID, course.getCourseGUID());
		params.addValue(CourseMapper.STUDENT_GUID, course.getStudentGUID());
		params.addValue(CourseMapper.INSTRUCTOR_GUID, course.getInstructorGUID());
		params.addValue(CourseMapper.START_DATE, course.getStartDate());
		params.addValue(CourseMapper.LOCATION, course.getLocation());
		params.addValue(CourseMapper.TOPIC, course.getTopic());
		params.addValue(CourseMapper.INSTRUCTOR_COMMENTS, course.getInstructorComments());
		params.addValue(CourseMapper.STUDENT_FEEDBACK, course.getStudentFeedback());
		params.addValue(CourseMapper.COURSE_STATUS, course.getCourseStatus().name());
		params.addValue(CourseMapper.COURSE_RATING, course.getCourseRating().name());
		params.addValue(CourseMapper.DISCOUNT, course.getDiscount());
		params.addValue(CourseMapper.SUBJECT, course.getSubject());
		jdbcTemplate.update(updateCourseByCourseGUID, params);
	}

}

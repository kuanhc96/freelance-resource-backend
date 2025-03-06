package com.example.freelance_resource_backend.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.CourseEntity;
import com.example.freelance_resource_backend.mapper.CourseMapper;

@Repository
@RequiredArgsConstructor
public class CourseRepository {
	private final CourseMapper courseMapper;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	private String getCourseByCourseGUID = "SELECT * FROM course WHERE course_guid = :courseGUID";
	private String getCoursesByStudentGUID = "SELECT * FROM course WHERE student_guid = :studentGUID";
	private String getCoursesByInstructorGUID = "SELECT * FROM course WHERE instructor_guid = :instructorGUID";
	private String getCoursesByStudentGUIDAndInstructorGUID = "SELECT * FROM course WHERE student_guid = :studentGUID AND instructor_guid = :instructorGUID";

	private String insertCourse = "INSERT INTO course (course_guid, student_guid, instructor_guid, start_date, location, topic, instructor_comments, student_feedback, course_category, course_status, course_rating, price) " +
			"VALUES (:courseGUID, :studentGUID, :instructorGUID, :startDate, :location, :topic, :instructorComments, :studentFeedback, :courseCategory, :courseStatus, :courseRating, :price)";

	private String updateCourseByCourseGUID = "UPDATE course " +
			"SET student_guid = :studentGUID, instructor_guid = :instructorGUID, start_date = :startDate, location = :location, " +
				"topic = :topic, instructor_comments = :instructorComments, student_feedback = :studentFeedback, course_category = :courseCategory, " +
				"course_status = :courseStatus, course_rating = :courseRating, price = :price " +
			"WHERE course_guid = :courseGUID";

	public CourseEntity getCourseByCourseGUID(String courseGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("courseGUID", courseGUID);
		return jdbcTemplate.queryForObject(getCourseByCourseGUID, params, courseMapper);
	}

	public List<CourseEntity> getCoursesByStudentGUID(String studentGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("studentGUID", studentGUID);
		return jdbcTemplate.query(getCoursesByStudentGUID, params, courseMapper);
	}

	public List<CourseEntity> getCoursesByInstructorGUID(String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("instructorGUID", instructorGUID);
		return jdbcTemplate.query(getCoursesByInstructorGUID, params, courseMapper);
	}

	public List<CourseEntity> getCoursesByStudentGUIDAndInstructorGUID(String studentGUID, String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("studentGUID", studentGUID);
		params.addValue("instructorGUID", instructorGUID);
		return jdbcTemplate.query(getCoursesByStudentGUIDAndInstructorGUID, params, courseMapper);
	}

	public void insertCourse(CourseEntity course) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("courseGUID", course.getCourseGUID());
		params.addValue("studentGUID", course.getStudentGUID());
		params.addValue("instructorGUID", course.getInstructorGUID());
		params.addValue("startDate", course.getStartDate());
		params.addValue("location", course.getLocation());
		params.addValue("topic", course.getTopic());
		params.addValue("instructorComments", course.getInstructorComments());
		params.addValue("studentFeedback", course.getStudentFeedback());
		params.addValue("courseCategory", course.getCourseCategory().name());
		params.addValue("courseStatus", course.getCourseStatus().name());
		params.addValue("courseRating", course.getCourseRating().name());
		params.addValue("price", course.getPrice());
		jdbcTemplate.update(insertCourse, params);
	}

	public void updateCourseByCourseGUID(CourseEntity course) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("courseGUID", course.getCourseGUID());
		params.addValue("studentGUID", course.getStudentGUID());
		params.addValue("instructorGUID", course.getInstructorGUID());
		params.addValue("startDate", course.getStartDate());
		params.addValue("location", course.getLocation());
		params.addValue("topic", course.getTopic());
		params.addValue("instructorComments", course.getInstructorComments());
		params.addValue("studentFeedback", course.getStudentFeedback());
		params.addValue("courseCategory", course.getCourseCategory().name());
		params.addValue("courseStatus", course.getCourseStatus().name());
		params.addValue("courseRating", course.getCourseRating().name());
		params.addValue("price", course.getPrice());
		jdbcTemplate.update(updateCourseByCourseGUID, params);
	}

}

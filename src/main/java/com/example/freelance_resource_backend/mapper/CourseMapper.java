package com.example.freelance_resource_backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.freelance_resource_backend.entities.CourseEntity;
import com.example.freelance_resource_backend.enums.CourseRating;
import com.example.freelance_resource_backend.enums.CourseStatus;

@Component
public class CourseMapper implements RowMapper<CourseEntity> {
	public static final String COURSE_ID = "course_id";
	public static final String COURSE_GUID = "course_guid";
	public static final String STUDENT_GUID = "student_guid";
	public static final String INSTRUCTOR_GUID = "instructor_guid";
	public static final String START_DATE = "start_date";
	public static final String LOCATION = "location";
	public static final String TOPIC = "topic";
	public static final String INSTRUCTOR_COMMENTS = "instructor_comments";
	public static final String STUDENT_FEEDBACK = "student_feedback";
	public static final String COURSE_STATUS = "course_status";
	public static final String COURSE_RATING = "course_rating";
	public static final String DISCOUNT = "discount";


	@Override
	public CourseEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		CourseEntity course = CourseEntity.builder()
				.courseId(rs.getLong(COURSE_ID))
				.courseGUID(rs.getString(COURSE_GUID))
				.studentGUID(rs.getString(STUDENT_GUID))
				.instructorGUID(rs.getString(INSTRUCTOR_GUID))
				.startDate(rs.getTimestamp(START_DATE).toLocalDateTime())
				.location(rs.getString(LOCATION))
				.topic(rs.getString(TOPIC))
				.instructorComments(rs.getString(INSTRUCTOR_COMMENTS))
				.studentFeedback(rs.getString(STUDENT_FEEDBACK))
				.courseStatus(CourseStatus.getValue(rs.getString(COURSE_STATUS)))
				.courseRating(CourseRating.getValue(rs.getInt(COURSE_RATING)))
				.discount(rs.getInt(DISCOUNT))
				.build();
		return course;
	}
}

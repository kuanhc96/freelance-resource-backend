package com.example.freelance_resource_backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.freelance_resource_backend.entities.CourseEntity;
import com.example.freelance_resource_backend.enums.CourseCategory;
import com.example.freelance_resource_backend.enums.CourseRating;
import com.example.freelance_resource_backend.enums.CourseStatus;

@Component
public class CourseMapper implements RowMapper<CourseEntity> {
	public static final String COURSE_ID = "course_id";
	public static final String COURSE_GUID = "course_guid";
	public static final String COURSE_CATEGORY = "course_category";
	public static final String START_DATE = "start_date";
	public static final String LOCATION = "location";
	public static final String TOPIC = "topic";
	public static final String INSTRUCTOR_COMMENTS = "instructor_comments";
	public static final String STUDENT_FEEDBACK = "student_feedback";
	public static final String COURSE_STATUS = "course_status";
	public static final String COURSE_RATING = "course_rating";
	public static final String PRICE = "price";


	@Override
	public CourseEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		CourseEntity course = new CourseEntity();
		course.setCourseId(rs.getLong(COURSE_ID));
		course.setCourseGUID(rs.getString(COURSE_GUID));
		course.setCourseCategory(CourseCategory.getValue(rs.getString(COURSE_CATEGORY)));
		course.setStartDate(rs.getTimestamp(START_DATE).toLocalDateTime());
		course.setLocation(rs.getString(LOCATION));
		course.setTopic(rs.getString(TOPIC));
		course.setInstructorComments(rs.getString(INSTRUCTOR_COMMENTS));
		course.setStudentFeedback(rs.getString(STUDENT_FEEDBACK));
		course.setCourseStatus(CourseStatus.getValue(rs.getString(COURSE_STATUS)));
		course.setCourseRating(CourseRating.getValue(rs.getInt(COURSE_RATING)));
		course.setPrice(rs.getInt(PRICE));
		return course;
	}
}

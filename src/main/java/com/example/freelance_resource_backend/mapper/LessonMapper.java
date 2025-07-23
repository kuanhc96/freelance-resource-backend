package com.example.freelance_resource_backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.freelance_resource_backend.entities.LessonEntity;
import com.example.freelance_resource_backend.enums.LessonRating;
import com.example.freelance_resource_backend.enums.LessonStatus;

@Component
public class LessonMapper implements RowMapper<LessonEntity> {
	public static final String LESSON_ID = "lesson_id";
	public static final String LESSON_GUID = "lesson_guid";
	public static final String STUDENT_GUID = "student_guid";
	public static final String STUDENT_NAME = "student_name";
	public static final String INSTRUCTOR_GUID = "instructor_guid";
	public static final String INSTRUCTOR_NAME = "instructor_name";
	public static final String START_DATE = "start_date";
	public static final String END_DATE = "start_date";
	public static final String LOCATION = "location";
	public static final String TOPIC = "topic";
	public static final String INSTRUCTOR_COMMENTS = "instructor_comments";
	public static final String STUDENT_FEEDBACK = "student_feedback";
	public static final String LESSON_STATUS = "lesson_status";
	public static final String LESSON_RATING = "lesson_rating";
	public static final String SUBJECT_GUID = "subject_guid";
	public static final String SUBJECT_NAME = "subject_name";
	public static final String TRANSACTION_GUID = "transaction_guid";
	public static final String DURATION = "duration";


	@Override
	public LessonEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		LessonEntity lesson = LessonEntity.builder()
				.lessonId(rs.getLong(LESSON_ID))
				.lessonGUID(rs.getString(LESSON_GUID))
				.studentGUID(rs.getString(STUDENT_GUID))
				.studentName(rs.getString(STUDENT_NAME))
				.instructorGUID(rs.getString(INSTRUCTOR_GUID))
				.instructorName(rs.getString(INSTRUCTOR_NAME))
				.startDate(rs.getTimestamp(START_DATE).toLocalDateTime())
				.endDate(rs.getTimestamp(END_DATE).toLocalDateTime())
				.location(rs.getString(LOCATION))
				.topic(rs.getString(TOPIC))
				.instructorComments(rs.getString(INSTRUCTOR_COMMENTS))
				.studentFeedback(rs.getString(STUDENT_FEEDBACK))
				.lessonStatus(LessonStatus.getValue(rs.getString(LESSON_STATUS)))
				.lessonRating(LessonRating.getValue(rs.getInt(LESSON_RATING)))
				.subjectGUID(rs.getString(SUBJECT_GUID))
				.subjectName(rs.getString(SUBJECT_NAME))
				.duration(rs.getInt(DURATION))
				.build();
		return lesson;
	}
}

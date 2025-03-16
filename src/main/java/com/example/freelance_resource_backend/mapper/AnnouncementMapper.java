package com.example.freelance_resource_backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.freelance_resource_backend.entities.AnnouncementEntity;
import com.example.freelance_resource_backend.enums.AnnouncementStatus;

@Component
public class AnnouncementMapper implements RowMapper<AnnouncementEntity> {
	public static final String ANNOUNCEMENT_ID = "announcementId";
	public static final String ANNOUNCEMENT_GUID = "announcementGUID";
	public static final String TITLE = "title";
	public static final String ANNOUNCEMENT = "announcement";
	public static final String INSTRUCTOR_GUID = "instructorGUID";
	public static final String CREATED_DATE = "createdDate";
	public static final String ANNOUNCEMENT_STATUS = "announcementStatus";

	@Override
	public AnnouncementEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		AnnouncementEntity announcement = AnnouncementEntity.builder()
				.announcementId(rs.getLong(ANNOUNCEMENT_ID))
				.announcementGUID(rs.getString(ANNOUNCEMENT_GUID))
				.title(rs.getString(TITLE))
				.announcement(rs.getString(ANNOUNCEMENT))
				.instructorGUID(rs.getString(INSTRUCTOR_GUID))
				.createdDate(rs.getTimestamp(CREATED_DATE).toLocalDateTime())
				.announcementStatus(AnnouncementStatus.getValue(rs.getString(ANNOUNCEMENT_STATUS)))
				.build();
		return announcement;
	}

}

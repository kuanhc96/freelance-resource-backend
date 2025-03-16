package com.example.freelance_resource_backend.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.AnnouncementEntity;
import com.example.freelance_resource_backend.mapper.AnnouncementMapper;

@Repository
@RequiredArgsConstructor
public class AnnouncementRepository {
	private final AnnouncementMapper announcementMapper;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	private String getAnnouncementsByInstructorGUID = "SELECT * FROM announcements WHERE instructor_guid = :instructorGUID";

	private String insertAnnouncement = "INSERT INTO announcements " +
			"(announcement_guid, title, announcement, instructor_guid, created_date, announcement_status) " +
			"VALUES (:announcement_guid, :title, :announcement, :instructor_guid, :created_date, :announcement_status)";

	public List<AnnouncementEntity> getAnnouncementsByInstructorGUID(String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(AnnouncementMapper.INSTRUCTOR_GUID, instructorGUID);
		return jdbcTemplate.query(getAnnouncementsByInstructorGUID, params, announcementMapper);
	}

	public void insertAnnouncement(AnnouncementEntity announcement) {
		Map<String, Object> params = Map.of(
				AnnouncementMapper.ANNOUNCEMENT_GUID, announcement.getAnnouncementGUID(),
				AnnouncementMapper.TITLE, announcement.getTitle(),
				AnnouncementMapper.ANNOUNCEMENT, announcement.getAnnouncement(),
				AnnouncementMapper.INSTRUCTOR_GUID, announcement.getInstructorGUID(),
				AnnouncementMapper.CREATED_DATE, announcement.getCreatedDate(),
				AnnouncementMapper.ANNOUNCEMENT_STATUS, announcement.getAnnouncementStatus()
		);
		jdbcTemplate.update(insertAnnouncement, params);
	}
}

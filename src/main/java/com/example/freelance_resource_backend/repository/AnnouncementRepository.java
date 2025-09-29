package com.example.freelance_resource_backend.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
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

	private String getAnnouncementsByInstructorGUID = "SELECT * FROM announcements WHERE instructor_guid = :instructor_guid";

	private String insertAnnouncement = "INSERT INTO announcements " +
			"(announcement_guid, title, announcement, instructor_guid, created_date, updated_date, announcement_status) " +
			"VALUES (:announcement_guid, :title, :announcement, :instructor_guid, :created_date, :updated_date, :announcement_status)";

	private String getAnnouncementByAnnouncementGUID = "SELECT * FROM announcements WHERE announcement_guid = :announcement_guid";

	private String updateAnnouncement = "UPDATE announcements SET " +
			"title = :title, " +
			"announcement = :announcement, " +
			"updated_date = :updated_date, " +
			"announcement_status = :announcement_status " +
			"WHERE announcement_guid = :announcement_guid";

	private String deleteAnnouncement = "DELETE FROM announcements WHERE announcement_guid = :announcement_guid";

	public void deleteAnnouncementByAnnouncementGUID(String announcementGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(AnnouncementMapper.ANNOUNCEMENT_GUID, announcementGUID);
		jdbcTemplate.update(deleteAnnouncement, params);
	}

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
				AnnouncementMapper.UPDATED_DATE, announcement.getUpdatedDate(),
				AnnouncementMapper.ANNOUNCEMENT_STATUS, announcement.getAnnouncementStatus().getValue()
		);
		jdbcTemplate.update(insertAnnouncement, params);
	}

	public Optional<AnnouncementEntity> getAnnouncementByAnnouncementGUID(String announcementGUID) {
		try {
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(AnnouncementMapper.ANNOUNCEMENT_GUID, announcementGUID);
			return Optional.ofNullable(jdbcTemplate.queryForObject(getAnnouncementByAnnouncementGUID, params, announcementMapper));
		} catch (DataAccessException e) {
			// Handle the case where no announcement is found
			return Optional.empty();
		}
	}

	public void updateAnnouncement(AnnouncementEntity announcement) {
		Map<String, Object> params = Map.of(
				AnnouncementMapper.ANNOUNCEMENT_GUID, announcement.getAnnouncementGUID(),
				AnnouncementMapper.TITLE, announcement.getTitle(),
				AnnouncementMapper.ANNOUNCEMENT, announcement.getAnnouncement(),
				AnnouncementMapper.UPDATED_DATE, announcement.getUpdatedDate(),
				AnnouncementMapper.ANNOUNCEMENT_STATUS, announcement.getAnnouncementStatus().getValue()
		);
		jdbcTemplate.update(updateAnnouncement, params);
	}
}

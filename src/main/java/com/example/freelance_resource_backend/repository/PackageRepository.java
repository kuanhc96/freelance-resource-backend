package com.example.freelance_resource_backend.repository;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.PackageEntity;
import com.example.freelance_resource_backend.mapper.PackageMapper;

@Repository
@RequiredArgsConstructor
public class PackageRepository {
	private final PackageMapper packageMapper;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public final String getPackageByPackageGUID = "SELECT * FROM packages WHERE package_guid = :package_guid";
	public final String getPackageByDiscountCodeAndSubjectGUID = "SELECT * FROM packages WHERE discount_code = :discount_code AND subject_guid = :subject_guid";
	public final String insertPackage = "INSERT INTO packages (package_guid, subject_guid, discount_code, number_of_lessons, discount_rate) " +
			"VALUES (:package_guid, :subject_guid, :discount_code, :number_of_lessons, :discount_rate)";

	public Optional<PackageEntity> getPackageByPackageGUID(String package_guid) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue(PackageMapper.PACKAGE_GUID, package_guid);
		try {
			return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(getPackageByPackageGUID, parameterSource, packageMapper));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public Optional<PackageEntity> getPackageByDiscountCodeAndSubjectGUID(String discountCode, String subjectGUID) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue(PackageMapper.DISCOUNT_CODE, discountCode);
		parameterSource.addValue(PackageMapper.SUBJECT_GUID, subjectGUID);
		try {
			return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(getPackageByDiscountCodeAndSubjectGUID, parameterSource, packageMapper));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public void insertPackage(PackageEntity packageEntity) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue(PackageMapper.PACKAGE_GUID, packageEntity.getPackageGUID());
		parameterSource.addValue(PackageMapper.SUBJECT_GUID, packageEntity.getSubjectGUID());
		parameterSource.addValue(PackageMapper.DISCOUNT_CODE, packageEntity.getDiscountCode());
		parameterSource.addValue(PackageMapper.NUMBER_OF_LESSONS, packageEntity.getNumberOfLessons());
		parameterSource.addValue(PackageMapper.DISCOUNT_RATE, packageEntity.getDiscountRate());
		namedParameterJdbcTemplate.update(insertPackage, parameterSource);
	}
}

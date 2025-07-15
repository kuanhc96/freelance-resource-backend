package com.example.freelance_resource_backend.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.PackageEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.PackageRepository;

@Service
@RequiredArgsConstructor
public class PackageService {
	private final PackageRepository packageRepository;

	public PackageEntity getPackageByPackageGUID(String packageGUID) throws ResourceNotFoundException {
		return packageRepository.getPackageByPackageGUID(packageGUID)
				.orElseThrow(() -> new ResourceNotFoundException("Package not found with GUID: " + packageGUID));
	}

	public PackageEntity getPackageByDiscountCodeAndSubjectGUID(String discountCode, String subjectGUID) throws ResourceNotFoundException {
		return packageRepository.getPackageByDiscountCodeAndSubjectGUID(discountCode, subjectGUID)
				.orElseThrow(() -> new ResourceNotFoundException("Package not found with discount code: " + discountCode + " and subject GUID: " + subjectGUID));
	}

	public PackageEntity createPackage(String subjectGUID, String discountCode, Integer numberOfLessons, Double discountRate) {
		PackageEntity packageEntity = PackageEntity.builder()
				.packageGUID(java.util.UUID.randomUUID().toString())
				.subjectGUID(subjectGUID)
				.discountCode(discountCode)
				.numberOfLessons(numberOfLessons)
				.discountRate(discountRate)
				.build();
		packageRepository.insertPackage(packageEntity);
		return packageEntity;
	}
}

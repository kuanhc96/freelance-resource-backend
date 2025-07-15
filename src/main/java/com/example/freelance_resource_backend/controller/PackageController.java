package com.example.freelance_resource_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.package_.CreatePackageRequest;
import com.example.freelance_resource_backend.dto.response.package_.CreatePackageResponse;
import com.example.freelance_resource_backend.dto.response.package_.GetPackageResponse;
import com.example.freelance_resource_backend.entities.PackageEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.service.PackageService;

@RestController
@RequestMapping("/packages")
@RequiredArgsConstructor
public class PackageController {
	private final PackageService packageService;

	@GetMapping("/{packageGUID}")
	public ResponseEntity<GetPackageResponse> getPackage(@PathVariable String packageGUID) throws ResourceNotFoundException {
		PackageEntity packageEntity = packageService.getPackageByPackageGUID(packageGUID);
		return ResponseEntity.ok(GetPackageResponse.builder()
				.packageGUID(packageEntity.getPackageGUID())
				.discountCode(packageEntity.getDiscountCode())
				.subjectGUID(packageEntity.getSubjectGUID())
				.numberOfLessons(packageEntity.getNumberOfLessons())
				.discountRate(packageEntity.getDiscountRate())
				.build());
	}

	@GetMapping("/subject/{subjectGUID}/{discountCode}")
	public ResponseEntity<GetPackageResponse> getPackage(@PathVariable String subjectGUID, @PathVariable String discountCode) throws ResourceNotFoundException {
		PackageEntity packageEntity = packageService.getPackageByDiscountCodeAndSubjectGUID(discountCode, subjectGUID);
		return ResponseEntity.ok(GetPackageResponse.builder()
				.packageGUID(packageEntity.getPackageGUID())
				.discountCode(packageEntity.getDiscountCode())
				.subjectGUID(packageEntity.getSubjectGUID())
				.numberOfLessons(packageEntity.getNumberOfLessons())
				.discountRate(packageEntity.getDiscountRate())
				.build());
	}

	@GetMapping("/subject/{subjectGUID}")
	public ResponseEntity<List<GetPackageResponse>> getPackageBySubject(@PathVariable String subjectGUID) throws ResourceNotFoundException {
		List<GetPackageResponse> packages = packageService.getPackagesBySubjectGUID(subjectGUID)
				.stream()
				.map(packageEntity -> GetPackageResponse.builder()
						.packageGUID(packageEntity.getPackageGUID())
						.discountCode(packageEntity.getDiscountCode())
						.subjectGUID(packageEntity.getSubjectGUID())
						.numberOfLessons(packageEntity.getNumberOfLessons())
						.discountRate(packageEntity.getDiscountRate())
						.build())
				.toList();

		return ResponseEntity.ok(packages);
	}

	@PostMapping("/create")
	public ResponseEntity<CreatePackageResponse> createPackage(@RequestBody CreatePackageRequest request) {
		PackageEntity packageEntity = packageService.createPackage(request.getSubjectGUID(), request.getDiscountCode(), request.getNumberOfLessons(), request.getDiscountRate());
		return ResponseEntity.ok(CreatePackageResponse.builder()
				.packageGUID(packageEntity.getPackageGUID())
				.subjectGUID(request.getSubjectGUID())
				.discountCode(request.getDiscountCode())
				.numberOfLessons(request.getNumberOfLessons())
				.discountRate(request.getDiscountRate())
				.build());
	}
}

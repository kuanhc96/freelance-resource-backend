package com.example.freelance_resource_backend.service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.response.lesson.GetLessonResponse;
import com.example.freelance_resource_backend.entities.LessonEntity;
import com.example.freelance_resource_backend.entities.PackageEntity;
import com.example.freelance_resource_backend.entities.SubjectEntity;
import com.example.freelance_resource_backend.entities.TransactionEntity;
import com.example.freelance_resource_backend.enums.LessonFrequency;
import com.example.freelance_resource_backend.enums.LessonStatus;
import com.example.freelance_resource_backend.enums.TransactionStatus;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.LessonRepository;
import com.example.freelance_resource_backend.repository.PackageRepository;
import com.example.freelance_resource_backend.repository.SubjectRepository;
import com.example.freelance_resource_backend.repository.TransactionRepository;
import com.example.freelance_resource_backend.translator.LessonTranslator;

@Service
@RequiredArgsConstructor
public class LessonService {
	private final LessonRepository lessonRepository;
	private final SubjectRepository subjectRepository;
	private final PackageRepository packageRepository;
	private final TransactionRepository transactionRepository;

	public List<LessonEntity> precreateLessons(
			String studentGUID,
			String instructorGUID,
			String subjectGUID,
			String packageGUID,
			LocalDateTime startDate,
			LessonFrequency lessonFrequency,
			String location
	) throws ResourceNotFoundException {
		Optional<SubjectEntity> optionalSubject = subjectRepository.getSubjectBySubjectGUID(subjectGUID);
		Optional<PackageEntity> optionalPackage = packageRepository.getPackageByPackageGUID(packageGUID);
		if (optionalSubject.isPresent() && optionalPackage.isPresent()) {
			SubjectEntity subjectEntity = optionalSubject.get();
			PackageEntity packageEntity = optionalPackage.get();
			List<LessonEntity> lessonEntities = new LinkedList<>();
			for (int i = 0; i < packageEntity.getNumberOfLessons(); i++) {
				LessonStatus lessonStatus = startDate == null? LessonStatus.CREATED : LessonStatus.SCHEDULED;
				lessonEntities.add(
						LessonEntity.builder()
								.lessonGUID(UUID.randomUUID().toString())
								.studentGUID(studentGUID)
								.instructorGUID(instructorGUID)
								.startDate(startDate == null? LocalDateTime.MAX: startDate)
								.endDate(startDate == null? LocalDateTime.MAX: startDate.plusMinutes(subjectEntity.getDuration()))
								.location(location)
								.lessonStatus(lessonStatus)
								.subjectGUID(subjectGUID)
								.build()
				);
				switch (lessonFrequency) {
					case DAILY -> startDate = startDate.plusDays(1);
					case WEEKLY -> startDate = startDate.plusWeeks(1);
					case MONTHLY -> startDate = startDate.plusMonths(1);
					default -> startDate = null;
				}
			}

			return lessonEntities;
		} else {
			throw new ResourceNotFoundException("subjectGUID: " + subjectGUID + " or packageGUID: " + packageGUID + " not found for instructorGUID: " + instructorGUID);
		}

	}

	@Transactional
	public String createLessons(
			List<LessonEntity> lessonEntities,
			String studentGUID,
			String instructorGUID,
			String subjectGUID,
			String packageGUID
	) throws ResourceNotFoundException {
		Optional<SubjectEntity> optionalSubject = subjectRepository.getSubjectBySubjectGUID(subjectGUID);
		Optional<PackageEntity> optionalPackage = packageRepository.getPackageByPackageGUID(packageGUID);
		if (optionalSubject.isPresent() && optionalPackage.isPresent()) {
			SubjectEntity subjectEntity = optionalSubject.get();
			PackageEntity packageEntity = optionalPackage.get();
			String transactionGUID = UUID.randomUUID().toString();

			TransactionStatus transactionStatus = TransactionStatus.PENDING;
			LocalDateTime creationDate = LocalDateTime.now();
			int paymentAmount = (int) (packageEntity.getNumberOfLessons() * subjectEntity.getPrice() * packageEntity.getDiscountRate());
			TransactionEntity transactionEntity = TransactionEntity.builder()
					.transactionGUID(transactionGUID)
					.studentGUID(studentGUID)
					.instructorGUID(instructorGUID)
					.subjectGUID(subjectGUID)
					.packageGUID(packageGUID)
					.transactionStatus(transactionStatus)
					.paymentAmount(paymentAmount)
					.creationDate(creationDate).build();
			transactionRepository.insertTransaction(transactionEntity);

			for (LessonEntity newLessonEntity : lessonEntities) {
				newLessonEntity.setSubjectGUID(subjectGUID);
				lessonRepository.insertLesson(newLessonEntity);
			}
			return transactionGUID;
		} else {
			throw new ResourceNotFoundException("subjectGUID: " + subjectGUID + " or packageGUID: " + packageGUID + " not found for instructorGUID: " + instructorGUID);
		}
	}

	public void updateLessonStudent(String lessonGUID, String studentGUID) {
		LessonEntity existingLessonEntity = lessonRepository.getLessonByLessonGUID(lessonGUID);
		existingLessonEntity.setStudentGUID(studentGUID);
		lessonRepository.updateLessonByLessonGUID(existingLessonEntity);
	}

	public void updateLessonInstructor(String lessonGUID, String instructorGUID) {
		LessonEntity existingLessonEntity = lessonRepository.getLessonByLessonGUID(lessonGUID);
		existingLessonEntity.setInstructorGUID(instructorGUID);
		lessonRepository.updateLessonByLessonGUID(existingLessonEntity);
	}

	public void updateLessonStartDate(String lessonGUID, LocalDateTime startDate) {
		LessonEntity existingLessonEntity = lessonRepository.getLessonByLessonGUID(lessonGUID);
		existingLessonEntity.setStartDate(startDate);
		lessonRepository.updateLessonByLessonGUID(existingLessonEntity);
	}

	public List<GetLessonResponse> getLessons(String studentGUID, String instructorGUID) throws ResourceNotFoundException {
		List<LessonEntity> lessonEntities = new LinkedList<>();
		if (StringUtils.isNotBlank(studentGUID) && StringUtils.isNotBlank(instructorGUID)) {
			lessonEntities = lessonRepository.getLessonsByStudentGUIDAndInstructorGUID(studentGUID, instructorGUID);
		} else if (StringUtils.isNotBlank(studentGUID)) {
			lessonEntities = lessonRepository.getLessonsByStudentGUID(studentGUID);
		} else if (StringUtils.isNotBlank(instructorGUID)) {
			lessonEntities = lessonRepository.getLessonsByInstructorGUID(instructorGUID);
		}

		if (lessonEntities.isEmpty()) {
			throw new ResourceNotFoundException("No lessons found for studentGUID: " + studentGUID + " or instructorGUID: " + instructorGUID);
		}

		return lessonEntities.stream().map(LessonTranslator::toDto).collect(Collectors.toList());
	}
}

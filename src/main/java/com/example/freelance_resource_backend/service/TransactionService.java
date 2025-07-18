package com.example.freelance_resource_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.TransactionEntity;
import com.example.freelance_resource_backend.enums.TransactionStatus;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.repository.LessonRepository;
import com.example.freelance_resource_backend.repository.TransactionRepository;

@Service
@RequiredArgsConstructor
public class TransactionService {
	private final TransactionRepository transactionRepository;
	private final LessonRepository lessonRepository;

	public TransactionEntity getTransactionByTransactionGUID(String transactionGUID) {
		return transactionRepository.getTransactionByTransactionGUID(transactionGUID);
	}

	public List<TransactionEntity> getTransactionsByStudentGUID(String studentGUID) {
		return transactionRepository.getTransactionsByStudentGUID(studentGUID);
	}

	public List<TransactionEntity> getTransactionsByInstructorGUID(String instructorGUID) {
		return transactionRepository.getTransactionsByInstructorGUID(instructorGUID);
	}

	public TransactionEntity createTransaction(String studentGUID, String instructorGUID, Integer paymentAmount, String comments) {
		String transactionGUID = UUID.randomUUID().toString();
		TransactionStatus transactionStatus = TransactionStatus.PENDING;
		LocalDateTime creationDate = LocalDateTime.now();
		TransactionEntity transactionEntity = TransactionEntity.builder()
				.transactionGUID(transactionGUID)
				.studentGUID(studentGUID)
				.instructorGUID(instructorGUID)
				.paymentAmount(paymentAmount)
				.comments(comments)
				.transactionStatus(transactionStatus)
				.creationDate(creationDate)
				.confirmedDate(null)
				.canceledDate(null)
				.build();
		transactionRepository.insertTransaction(transactionEntity);
		return transactionEntity;
	}

	public TransactionEntity updateTransactionComments(String transactionGUID, String comments) {
		TransactionEntity existingTransactionEntity = transactionRepository.getTransactionByTransactionGUID(transactionGUID);
		existingTransactionEntity.setComments(comments);
		transactionRepository.updateTransaction(existingTransactionEntity);
		return existingTransactionEntity;
	}

	@Transactional
	public void deleteTransaction(String transactionGUID) throws ResourceNotFoundException {
		TransactionEntity existingTransactionEntity = transactionRepository.getTransactionByTransactionGUID(transactionGUID);
		if (existingTransactionEntity != null) {
			lessonRepository.deleteLessonsByTransactionGUID(transactionGUID);
			transactionRepository.deleteTransaction(transactionGUID);
		} else {
			throw new ResourceNotFoundException("Transaction with GUID: " + transactionGUID + " does not exist.");
		}
	}
}

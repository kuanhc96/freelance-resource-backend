package com.example.freelance_resource_backend.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.TransactionEntity;
import com.example.freelance_resource_backend.enums.TransactionStatus;
import com.example.freelance_resource_backend.repository.TransactionRepository;

@Service
@RequiredArgsConstructor
public class TransactionService {
	private final TransactionRepository transactionRepository;

	public TransactionEntity getTransactionByTransactionGUID(String transactionGUID) {
		return transactionRepository.getTransactionByTransactionGUID(transactionGUID);
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

	public TransactionEntity createTransaction(String studentGUID, String instructorGUID, Integer paymentAmount) {
		return createTransaction(studentGUID, instructorGUID, paymentAmount, "");
	}

	public TransactionEntity updateTransactionComments(String transactionGUID, String comments) {
		TransactionEntity existingTransactionEntity = transactionRepository.getTransactionByTransactionGUID(transactionGUID);
		existingTransactionEntity.setComments(comments);
		transactionRepository.updateTransaction(existingTransactionEntity);
		return existingTransactionEntity;
	}
}

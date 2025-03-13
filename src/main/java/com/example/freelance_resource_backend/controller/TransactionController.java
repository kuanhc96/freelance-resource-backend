package com.example.freelance_resource_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.transaction.CreateTransactionRequest;
import com.example.freelance_resource_backend.dto.response.transaction.CreateTransactionResponse;
import com.example.freelance_resource_backend.dto.response.transaction.ReadTransactionResponse;
import com.example.freelance_resource_backend.entities.TransactionEntity;
import com.example.freelance_resource_backend.service.TransactionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

	private final TransactionService transactionService;

	@PostMapping("/createTransaction")
	public ResponseEntity<CreateTransactionResponse> createTransaction(CreateTransactionRequest request) {
		String studentGUID = request.getStudentGUID();
		String instructorGUID = request.getInstructorGUID();
		Integer paymentAmount = request.getPaymentAmount();
		String comments = request.getComments();
		TransactionEntity transactionEntity = transactionService.createTransaction(studentGUID, instructorGUID, paymentAmount, comments);
		return ResponseEntity.ok(CreateTransactionResponse.builder()
				.transactionGUID(transactionEntity.getTransactionGUID())
				.creationDate(transactionEntity.getCreationDate())
				.studentGUID(studentGUID)
				.instructorGUID(instructorGUID)
				.paymentAmount(paymentAmount)
				.comments(comments)
				.build());
	}

	@GetMapping("/{transactionGUID}")
	public ResponseEntity<ReadTransactionResponse> getTransaction(@PathVariable String transactionGUID) {
		TransactionEntity transactionEntity = transactionService.getTransactionByTransactionGUID(transactionGUID);
		ReadTransactionResponse response = ReadTransactionResponse.builder()
				.transactionGUID(transactionEntity.getTransactionGUID())
				.studentGUID(transactionEntity.getStudentGUID())
				.instructorGUID(transactionEntity.getInstructorGUID())
				.creationDate(transactionEntity.getCreationDate())
				.paymentAmount(transactionEntity.getPaymentAmount())
				.transactionStatus(transactionEntity.getTransactionStatus())
				.creationDate(transactionEntity.getCreationDate())
				.confirmedDate(transactionEntity.getConfirmedDate())
				.canceledDate(transactionEntity.getCanceledDate())
				.comments(transactionEntity.getComments())
				.build();
		return ResponseEntity.ok(response);
	}
}

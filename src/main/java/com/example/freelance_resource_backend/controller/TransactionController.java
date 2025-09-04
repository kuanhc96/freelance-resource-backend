package com.example.freelance_resource_backend.controller;

import static com.example.freelance_resource_backend.constants.ApplicationConstants.INSTRUCTOR;
import static com.example.freelance_resource_backend.constants.ApplicationConstants.INSTRUCTOR_OR_STUDENT;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.dto.request.transaction.CreateTransactionRequest;
import com.example.freelance_resource_backend.dto.response.transaction.CreateTransactionResponse;
import com.example.freelance_resource_backend.dto.response.transaction.GetTransactionResponse;
import com.example.freelance_resource_backend.entities.TransactionEntity;
import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;
import com.example.freelance_resource_backend.service.TransactionService;
import com.example.freelance_resource_backend.translator.TransactionTranslator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

	private final TransactionService transactionService;

	@PostMapping("/createTransaction")
	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public ResponseEntity<CreateTransactionResponse> createTransaction(@RequestBody CreateTransactionRequest request) {
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

	@GetMapping("/student/{studentGUID}")
	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public ResponseEntity<List<GetTransactionResponse>> getTransactionByStudent(@PathVariable String studentGUID) {
		List<TransactionEntity> transactionEntities = transactionService.getTransactionsByStudentGUID(studentGUID);
		List<GetTransactionResponse> responses = transactionEntities.stream().map(TransactionTranslator::toDto).toList();
		return ResponseEntity.ok(responses);
	}

	@GetMapping("/instructor/{instructorGUID}")
	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public ResponseEntity<List<GetTransactionResponse>> getTransactionByInstructor(@PathVariable String instructorGUID) {
		List<TransactionEntity> transactionEntities = transactionService.getTransactionsByInstructorGUID(instructorGUID);
		List<GetTransactionResponse> responses = transactionEntities.stream().map(TransactionTranslator::toDto).toList();
		return ResponseEntity.ok(responses);
	}

	@GetMapping("/{transactionGUID}")
	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public ResponseEntity<GetTransactionResponse> getTransaction(@PathVariable String transactionGUID) {
		TransactionEntity transactionEntity = transactionService.getTransactionByTransactionGUID(transactionGUID);
		GetTransactionResponse response = TransactionTranslator.toDto(transactionEntity);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{transactionGUID}")
	@PreAuthorize(INSTRUCTOR)
	public ResponseEntity<Void> deleteTransaction(@PathVariable String transactionGUID) throws ResourceNotFoundException {
		transactionService.deleteTransaction(transactionGUID);
		return ResponseEntity.noContent().build();
	}
}

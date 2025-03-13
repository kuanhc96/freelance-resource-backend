package com.example.freelance_resource_backend.dto.response.transaction;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.TransactionStatus;

@Data
@Builder
public class CreateTransactionResponse {
	private String transactionGUID;
	private String studentGUID;
	private String instructorGUID;
	private TransactionStatus transactionStatus;
	private Integer paymentAmount;
	private LocalDateTime creationDate;
	private String comments;
}

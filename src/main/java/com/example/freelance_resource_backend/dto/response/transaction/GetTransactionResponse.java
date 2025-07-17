package com.example.freelance_resource_backend.dto.response.transaction;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.TransactionStatus;

@Data
@Builder
public class GetTransactionResponse {
	private String studentName;
	private String instructorName;
	private String subjectName;
	private String subjectDescription;
	private String discountCode;
	private Integer numberOfLessons;
	private Double discountRate;
	private TransactionStatus transactionStatus;
	private Integer paymentAmount;
	private LocalDateTime creationDate;
	private LocalDateTime confirmedDate;
	private LocalDateTime canceledDate;
	private String comments;
}

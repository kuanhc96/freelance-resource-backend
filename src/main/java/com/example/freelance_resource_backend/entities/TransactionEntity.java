package com.example.freelance_resource_backend.entities;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.TransactionStatus;

@Data
@Builder
public class TransactionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	private String studentName;
	private String instructorName;
	private String subjectName;
	private String subjectDescription;
	private String discountCode;
	private Integer numberOfLessons;
	private Double discountRate;
	private String transactionGUID;
	private String studentGUID;
	private String instructorGUID;
	private String subjectGUID;
	private String packageGUID;
	private TransactionStatus transactionStatus;
	private Integer paymentAmount;
	private LocalDateTime creationDate;
	private LocalDateTime confirmedDate;
	private LocalDateTime canceledDate;
	private String comments;

}

package com.example.freelance_resource_backend.translator;

import com.example.freelance_resource_backend.dto.response.transaction.GetTransactionResponse;
import com.example.freelance_resource_backend.entities.TransactionEntity;

public class TransactionTranslator {
	public static GetTransactionResponse toDto(TransactionEntity transactionEntity) {
		return GetTransactionResponse.builder()
				.subjectName(transactionEntity.getSubjectName())
				.subjectDescription(transactionEntity.getSubjectDescription())
				.studentName(transactionEntity.getStudentName())
				.instructorName(transactionEntity.getInstructorName())
				.discountCode(transactionEntity.getDiscountCode())
				.numberOfLessons(transactionEntity.getNumberOfLessons())
				.discountRate(transactionEntity.getDiscountRate())
				.transactionStatus(transactionEntity.getTransactionStatus())
				.paymentAmount(transactionEntity.getPaymentAmount())
				.creationDate(transactionEntity.getCreationDate())
				.confirmedDate(transactionEntity.getConfirmedDate())
				.canceledDate(transactionEntity.getCanceledDate())
				.comments(transactionEntity.getComments())
				.build();
	}
}

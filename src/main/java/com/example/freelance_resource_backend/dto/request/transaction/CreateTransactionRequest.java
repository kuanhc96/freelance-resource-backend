package com.example.freelance_resource_backend.dto.request.transaction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTransactionRequest {
	private String studentGUID;
	private String instructorGUID;
	private Integer paymentAmount;
	private String comments;
}

package com.example.freelance_resource_backend.dto.request.subscription;

import lombok.Getter;

@Getter
public class SubscribeRequest {
	private String studentGUID;
	private String instructorGUID;
}

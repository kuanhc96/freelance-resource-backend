package com.example.freelance_resource_backend.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import com.example.freelance_resource_backend.enums.UserStatus;


@Data
public class InstructorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long instructorId;
	private String instructorGUID;
	private String instructorName;
	private String email;
	private Integer birthYear;
	private Integer birthMonth;
	private Integer birthDay;
	private UserStatus status;
	private Integer revenue;
	// TODO: add fields for fetching google calendar events
}

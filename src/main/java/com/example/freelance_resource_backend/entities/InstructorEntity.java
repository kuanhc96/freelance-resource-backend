package com.example.freelance_resource_backend.entities;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.UserStatus;


@Data
@Builder
public class InstructorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long instructorId;
	private String instructorGUID;
	private String instructorName;
	private String email;
	private LocalDate birthday;
	private String gender;
	private String description;
	private UserStatus status;
	private Integer revenue;
	// TODO: add fields for fetching google calendar events
}

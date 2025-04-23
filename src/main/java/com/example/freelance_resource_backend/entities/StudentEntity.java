package com.example.freelance_resource_backend.entities;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import com.example.freelance_resource_backend.enums.Gender;
import com.example.freelance_resource_backend.enums.UserStatus;


@Table(name = "students")
@Data
@Builder
public class StudentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long studentId;
	private String studentGUID;
	private String studentName;
	private String email;
	private LocalDate birthday;
	private UserStatus status;
	private Integer deposit;
	private Gender gender;
	private String description;

}

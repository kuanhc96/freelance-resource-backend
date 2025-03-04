package com.example.freelance_resource_backend.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

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
	private Integer birthYear;
	private Integer birthMonth;
	private Integer birthDay;
	private UserStatus status;
	private Integer deposit;

}

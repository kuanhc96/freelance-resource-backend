package com.example.freelance_resource_backend.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.example.freelance_resource_backend.entities.TransactionEntity;
import com.example.freelance_resource_backend.mapper.TransactionMapper;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {
	private final TransactionMapper transactionMapper;
	private final NamedParameterJdbcTemplate jdbcTemplate;

	private String getTransactionsByStudentGUID = "SELECT * FROM transactions WHERE student_guid = :student_guid";
	private String getTransactionsByInstructorGUID = "SELECT * FROM transactions WHERE instructor_guid = :instructor_guid";
	private String getTransactionsByStudentGUIDAndInstructorGUID = "SELECT * FROM transactions WHERE student_guid = :student_guid AND instructor_guid = :instructor_guid";
	private String getTransactionByTransactionGUID = "SELECT * FROM transactions WHERE transaction_guid = :transaction_guid";
	private String insertTransaction = "INSERT INTO transactions " +
			"(transaction_guid, subject_guid, package_guid, student_guid, instructor_guid, transaction_status, payment_amount, creation_date, confirmed_date, canceled_date, comments) " +
			"VALUES (:transaction_guid, :subject_guid, :package_guid, :student_guid, :instructor_guid, :transaction_status, :payment_amount, :creation_date, :confirmed_date, :canceled_date, :comments)";
	private String updateTransaction = "UPDATE transactions " +
			"SET transaction_status = :transaction_status, confirmed_date = :confirmed_date, canceled_date = :canceled_date, comments = :comments" +
			" WHERE transaction_guid = :transaction_guid";

	public TransactionEntity getTransactionsByInstructorGUID(String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TransactionMapper.INSTRUCTOR_GUID, instructorGUID);
		return jdbcTemplate.queryForObject(getTransactionsByInstructorGUID, params, transactionMapper);
	}

	public TransactionEntity getTransactionsByStudentGUID(String studentGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TransactionMapper.STUDENT_GUID, studentGUID);
		return jdbcTemplate.queryForObject(getTransactionsByStudentGUID, params, transactionMapper);
	}

	public TransactionEntity getTransactionsByStudentGUIDAndInstructorGUID(String studentGUID, String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TransactionMapper.STUDENT_GUID, studentGUID);
		params.addValue(TransactionMapper.INSTRUCTOR_GUID, instructorGUID);
		return jdbcTemplate.queryForObject(getTransactionsByStudentGUIDAndInstructorGUID, params, transactionMapper);
	}

	public TransactionEntity getTransactionByTransactionGUID(String transactionGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TransactionMapper.TRANSACTION_GUID, transactionGUID);
		return jdbcTemplate.queryForObject(getTransactionByTransactionGUID, params, transactionMapper);
	}

	public void updateTransaction(TransactionEntity transaction) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TransactionMapper.TRANSACTION_GUID, transaction.getTransactionGUID());
		params.addValue(TransactionMapper.TRANSACTION_STATUS, transaction.getTransactionStatus().name());
		params.addValue(TransactionMapper.CONFIRMED_DATE, transaction.getConfirmedDate());
		params.addValue(TransactionMapper.CANCELED_DATE, transaction.getCanceledDate());
		params.addValue(TransactionMapper.COMMENTS, transaction.getComments());
		jdbcTemplate.update(updateTransaction, params);
	}

	public void insertTransaction(TransactionEntity transaction) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TransactionMapper.TRANSACTION_GUID, transaction.getTransactionGUID());
		params.addValue(TransactionMapper.SUBJECT_GUID, transaction.getSubjectGUID());
		params.addValue(TransactionMapper.PACKAGE_GUID, transaction.getPackageGUID());
		params.addValue(TransactionMapper.STUDENT_GUID, transaction.getStudentGUID());
		params.addValue(TransactionMapper.INSTRUCTOR_GUID, transaction.getInstructorGUID());
		params.addValue(TransactionMapper.TRANSACTION_STATUS, transaction.getTransactionStatus().name());
		params.addValue(TransactionMapper.PAYMENT_AMOUNT, transaction.getPaymentAmount());
		params.addValue(TransactionMapper.CREATION_DATE, transaction.getCreationDate());
		params.addValue(TransactionMapper.CONFIRMED_DATE, transaction.getConfirmedDate());
		params.addValue(TransactionMapper.CANCELED_DATE, transaction.getCanceledDate());
		params.addValue(TransactionMapper.COMMENTS, transaction.getComments());
		jdbcTemplate.update(insertTransaction, params);
	}
}

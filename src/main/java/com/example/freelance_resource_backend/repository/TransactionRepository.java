package com.example.freelance_resource_backend.repository;

import java.util.List;

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

	private String getTransactionsByStudentGUID =
			"SELECT t.transaction_id, t.transaction_guid, t.student_guid, t.instructor_guid, " +
					"u1.`name` student_name, u2.`name` instructor_name, " +
					"s.subject_guid, s.subject_name, s.subject_description, " +
					"p.package_guid, p.discount_code, p.number_of_lessons, p.discount_rate, " +
					"t.transaction_status, t.payment_amount, t.creation_date, t.confirmed_date, t.canceled_date, t.comments " +
					"FROM transactions t " +
					"JOIN users u1 " +
					"ON t.student_guid = u1.user_guid " +
					"JOIN users u2 " +
					"ON t.instructor_guid = u2.user_guid " +
					"JOIN subjects s " +
					"ON t.subject_guid = s.subject_guid " +
					"JOIN package p " +
					"ON t.package_guid = p.package_guid " +
					"WHERE t.student_guid = :student_guid";
	private String getTransactionsByInstructorGUID =
			"SELECT t.transaction_id, t.transaction_guid, t.student_guid, t.instructor_guid, " +
					"u1.`name` student_name, u2.`name` instructor_name, " +
					"s.subject_guid, s.subject_name, s.subject_description, " +
					"p.package_guid, p.discount_code, p.number_of_lessons, p.discount_rate, " +
					"t.transaction_status, t.payment_amount, t.creation_date, t.confirmed_date, t.canceled_date, t.comments " +
					"FROM transactions t " +
					"JOIN users u1 " +
					"ON t.student_guid = u1.user_guid " +
					"JOIN users u2 " +
					"ON t.instructor_guid = u2.user_guid " +
					"JOIN subjects s " +
					"ON t.subject_guid = s.subject_guid " +
					"JOIN package p " +
					"ON t.package_guid = p.package_guid " +
					"WHERE t.instructor_guid = :instructor_guid";

	private String getTransactionsByStudentGUIDAndInstructorGUID =
			"SELECT u1.`name` student_name, u2.`name` instructor_name, s.subject_name, s.subject_description, p.discount_code, p.number_of_lessons, p.discount_rate, t.transaction_status, t.payment_amount, t.creation_date, t.confirmed_date, t.canceled_date, t.comments " +
					"FROM transactions t " +
					"JOIN users u1 " +
					"ON t.student_guid = u1.user_guid " +
					"JOIN users u2 " +
					"ON t.instructor_guid = u2.user_guid " +
					"JOIN subjects s " +
					"ON t.subject_guid = s.subject_guid " +
					"JOIN package p " +
					"ON t.package_guid = p.package_guid " +
					"WHERE t.instructor_guid = :instructor_guid " +
					"AND t.student_guid = :student_guid";
	private String getTransactionByTransactionGUID =
			"SELECT t.transaction_id, t.transaction_guid, t.student_guid, t.instructor_guid, " +
					"u1.`name` student_name, u2.`name` instructor_name, " +
					"s.subject_guid, s.subject_name, s.subject_description, " +
					"p.package_guid, p.discount_code, p.number_of_lessons, p.discount_rate, " +
					"t.transaction_status, t.payment_amount, t.creation_date, t.confirmed_date, t.canceled_date, t.comments " +
					"FROM transactions t " +
					"JOIN users u1 " +
					"ON t.student_guid = u1.user_guid " +
					"JOIN users u2 " +
					"ON t.instructor_guid = u2.user_guid " +
					"JOIN subjects s " +
					"ON t.subject_guid = s.subject_guid " +
					"JOIN package p " +
					"ON t.package_guid = p.package_guid " +
					"WHERE t.transaction_guid = :transaction_guid";
	private String insertTransaction = "INSERT INTO transactions " +
			"(transaction_guid, subject_guid, package_guid, student_guid, instructor_guid, transaction_status, payment_amount, creation_date, confirmed_date, canceled_date) " +
			"VALUES (:transaction_guid, :subject_guid, :package_guid, :student_guid, :instructor_guid, :transaction_status, :payment_amount, :creation_date, :confirmed_date, :canceled_date)";
	private String updateTransaction = "UPDATE transactions " +
			"SET transaction_status = :transaction_status, confirmed_date = :confirmed_date, canceled_date = :canceled_date, comments = :comments" +
			" WHERE transaction_guid = :transaction_guid";

	private String deleteTransaction = "DELETE FROM transactions WHERE transaction_guid = :transaction_guid";

	public List<TransactionEntity> getTransactionsByStudentGUID(String studentGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TransactionMapper.STUDENT_GUID, studentGUID);
		try {
			return jdbcTemplate.query(getTransactionsByStudentGUID, params, transactionMapper);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return List.of();
		}
	}

	public List<TransactionEntity> getTransactionsByInstructorGUID(String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TransactionMapper.INSTRUCTOR_GUID, instructorGUID);
		try {
			return jdbcTemplate.query(getTransactionsByInstructorGUID, params, transactionMapper);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return List.of();
		}
	}

	public List<TransactionEntity> getTransactionsByStudentGUIDAndInstructorGUID(String studentGUID, String instructorGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TransactionMapper.STUDENT_GUID, studentGUID);
		params.addValue(TransactionMapper.INSTRUCTOR_GUID, instructorGUID);
		try {
			return jdbcTemplate.query(getTransactionsByStudentGUIDAndInstructorGUID, params, transactionMapper);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return List.of();
		}
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
		jdbcTemplate.update(insertTransaction, params);
	}

	public void deleteTransaction(String transactionGUID) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TransactionMapper.TRANSACTION_GUID, transactionGUID);
		jdbcTemplate.update(deleteTransaction, params);
	}
}

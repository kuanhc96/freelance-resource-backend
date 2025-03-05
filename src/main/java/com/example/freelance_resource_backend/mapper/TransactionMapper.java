package com.example.freelance_resource_backend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.freelance_resource_backend.entities.TransactionEntity;
import com.example.freelance_resource_backend.enums.TransactionStatus;

@Component
public class TransactionMapper implements RowMapper<TransactionEntity> {
	public static final String TRANSACTION_ID = "transaction_id";
	public static final String TRANSACTION_GUID = "transaction_guid";
	public static final String STUDENT_GUID = "student_guid";
	public static final String INSTRUCTOR_GUID = "instructor_guid";
	public static final String TRANSACTION_STATUS = "transaction_status";
	public static final String PAYMENT_AMOUNT = "payment_amount";
	public static final String CREATION_DATE = "creation_date";
	public static final String CONFIRMED_DATE = "confirmed_date";
	public static final String CANCELED_DATE = "canceled_date";
	public static final String COMMENTS = "comments";

	@Override
	public TransactionEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return TransactionEntity.builder()
				.transactionId(rs.getLong(TRANSACTION_ID))
				.studentGUID(rs.getString(STUDENT_GUID))
				.instructorGUID(rs.getString(INSTRUCTOR_GUID))
				.transactionStatus(TransactionStatus.getValue(rs.getString(TRANSACTION_STATUS)))
				.paymentAmount(rs.getInt(PAYMENT_AMOUNT))
				.creationDate(rs.getTimestamp(CREATION_DATE).toLocalDateTime())
				.confirmedDate(rs.getTimestamp(CONFIRMED_DATE) != null ? rs.getTimestamp(CONFIRMED_DATE).toLocalDateTime() : null)
				.canceledDate(rs.getTimestamp(CANCELED_DATE) != null ? rs.getTimestamp(CANCELED_DATE).toLocalDateTime() : null)
				.comments(rs.getString(COMMENTS))
				.build();
	}
}

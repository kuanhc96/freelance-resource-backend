create TABLE transactions(
   transaction_id INT NOT NULL AUTO_INCREMENT,
   student_guid VARCHAR(100),
	instructor_guid varchar(100),
	transaction_status varchar(50) NOT NULL DEFAULT 'created',
	payment_amount INT,
	creation_date DATETIME,
	confirmed_date DATETIME,
	canceled_date DATETIME,
	comments VARCHAR(500),
	PRIMARY KEY (transaction_id)
);
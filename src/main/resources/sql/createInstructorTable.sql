create TABLE instructors(
   instructor_id INT NOT NULL AUTO_INCREMENT,
   instructor_guid VARCHAR(100),
   email VARCHAR(100),
	revenue INT NOT NULL DEFAULT 0,
	birth_year INT,
	birth_month INT,
	birth_day INT,
	user_status varchar(50) NOT NULL DEFAULT 'created',
	PRIMARY KEY (instructor_id)
);
create TABLE students(
   student_id INT NOT NULL AUTO_INCREMENT,
   student_guid VARCHAR(100) NOT NULL,
	email varchar(100) not null,
	student_name VARCHAR(100) NOT NULL,
	password varchar(500) not NULL,
	birth_month INT,
	birth_day INT,
	birth_year INT,
	PRIMARY KEY (student_id)
);

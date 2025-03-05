create TABLE courses(
   course_id INT NOT NULL AUTO_INCREMENT,
   course_guid VARCHAR(100),
   course_category VARCHAR(50) NOT NULL DEFAULT 'golf',
   start_date DATETIME(100),
	location VARCHAR(100),
	topic VARCHAR(100),
	instructor_comments VARCHAR(500),
	student_feedback VARCHAR(500),
	course_status VARCHAR(50) NOT NULL DEFAULT 'created',
	course_rating INT NOT NULL DEFAULT 0,
	price INT,
	PRIMARY KEY (course_id)
);
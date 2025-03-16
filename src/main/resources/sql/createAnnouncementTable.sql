create TABLE announcements(
   announcement_id INT NOT NULL AUTO_INCREMENT,
   announcement_guid VARCHAR(100) NOT NULL,
   title VARCHAR(100) NOT NULL,
	announcement VARCHAR(1000),
	instructor_guid VARCHAR(100) NOT NULL,
	created_date DATETIME,
	announcement_status VARCHAR(50) NOT NULL DEFAULT 'active',
	PRIMARY KEY (announcement_id)
);
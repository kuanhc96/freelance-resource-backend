create TABLE users(
   user_id INT NOT NULL AUTO_INCREMENT,
   user_guid VARCHAR(100) UNIQUE NOT NULL,
	email varchar(100) unique not null,
	password varchar(500) not NULL,
	ROLE VARCHAR(100) NOT NULL DEFAULT 'student',
	PRIMARY KEY (user_id)
);
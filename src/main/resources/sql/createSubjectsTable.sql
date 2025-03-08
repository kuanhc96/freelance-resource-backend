CREATE TABLE subjects (
    subject_id INT PRIMARY KEY AUTO_INCREMENT,
    subject_name VARCHAR(100) NOT NULL,
    instructor_guid VARCHAR(100) NOT NULL,
    price INT NOT NULL,
    UNIQUE (subject_name, instructor_guid)
);

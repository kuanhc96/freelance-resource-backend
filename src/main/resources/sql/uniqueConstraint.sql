ALTER TABLE students
ADD CONSTRAINT unique_fields UNIQUE (student_guid, email);

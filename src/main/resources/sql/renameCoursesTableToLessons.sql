-- 1. Rename the table
ALTER TABLE courses
RENAME TO lessons;

-- 2. Rename each column
ALTER TABLE lessons
  RENAME COLUMN course_id     TO lesson_id,
  RENAME COLUMN course_guid   TO lesson_guid,
  RENAME COLUMN course_status TO lesson_status,
  RENAME COLUMN course_rating TO lesson_rating;

-- 1. Add new columns
ALTER TABLE users
ADD COLUMN status VARCHAR(100),
ADD COLUMN name VARCHAR(255),
ADD COLUMN gender VARCHAR(100),
ADD COLUMN description TEXT,
ADD COLUMN birthday DATETIME,
ADD COLUMN profile_picture VARCHAR(255);

-- 2. Drop existing unique constraint on email (replace 'email' if the index name is different)
ALTER TABLE users DROP INDEX email;

-- 3. Add composite unique constraint on (email, role)
ALTER TABLE users
ADD CONSTRAINT unique_email_role UNIQUE (email, role);
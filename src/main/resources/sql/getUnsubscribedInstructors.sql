SELECT * FROM subscriptions;
SELECT *
FROM users u
WHERE u.role = 'instructor'
  AND u.user_guid NOT IN (
    SELECT s.instructor_guid
    FROM subscriptions s
    WHERE s.student_guid = '3b7adf98-c0b1-4fe5-959f-23ba833c74a0'
  );
  
SELECT u.*
FROM users u
LEFT JOIN subscriptions s 
  ON u.user_guid = s.instructor_guid
  AND s.student_guid = '3b7adf98-c0b1-4fe5-959f-23ba833c74a0'
WHERE u.role = 'instructor'
  AND s.instructor_guid IS NULL;



-- '3b7adf98-c0b1-4fe5-959f-23ba833c74a0'

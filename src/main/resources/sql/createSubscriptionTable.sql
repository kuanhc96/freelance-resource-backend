create TABLE subscriptions(
   subscription_id INT AUTO_INCREMENT PRIMARY KEY,
   instructor_guid VARCHAR(100) NOT NULL,
   student_guid VARCHAR(100) NOT NULL,
	UNIQUE KEY unique_subscription(instructor_guid, student_guid)
);
DROP TABLE IF EXISTS `user_credentials`;
CREATE TABLE `user_credentials` (
                                    `user_id` int NOT NULL AUTO_INCREMENT,
                                    `user_guid` varchar(100) NOT NULL,
                                    `email` varchar(100) NOT NULL,
                                    `password` varchar(500) NOT NULL,
                                    `role` varchar(100) NOT NULL DEFAULT 'student',
                                    PRIMARY KEY (`user_id`),
                                    UNIQUE KEY `user_guid` (`user_guid`),
                                    UNIQUE KEY `unique_email_role` (`email`,`ROLE`)
);
INSERT INTO `user_credentials` (`user_id`, `user_guid`, `email`, `role`, `password`) VALUES (18, '36946828-6696-4b83-be33-6093f2efef70', 'kuandalice@gmail.com', 'INSTRUCTOR', '{noop}Test1234');
INSERT INTO `user_credentials` (`user_id`, `user_guid`, `email`, `role`, `password`) VALUES (25, 'edf7e0b4-55bf-4302-8685-c546a78a05e9', 'kuantest@example.com', 'STUDENT', '{bcrypt}$2a$10$SR0z9KozjW.9hP/VxSZv9eP.u50.buh0ROsUtYbsdYXRDYTumj0fG');

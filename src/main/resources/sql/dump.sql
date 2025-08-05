-- MySQL dump 10.13  Distrib 9.0.1, for Linux (x86_64)
--
-- Host: localhost    Database: user
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `announcements`
--

DROP TABLE IF EXISTS `announcements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcements` (
  `announcement_id` int NOT NULL AUTO_INCREMENT,
  `announcement_guid` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `announcement` varchar(1000) DEFAULT NULL,
  `instructor_guid` varchar(100) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `announcement_status` varchar(50) NOT NULL DEFAULT 'active',
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`announcement_id`),
  UNIQUE KEY `unique_announcement_guid` (`announcement_guid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcements`
--

LOCK TABLES `announcements` WRITE;
/*!40000 ALTER TABLE `announcements` DISABLE KEYS */;
INSERT INTO `announcements` VALUES (25,'f66f6d22-c378-46ad-8f16-f126b5dabd4c','lorem ipsum','Lorem ipsum wtf! wtf!!! LOL LOLOLOL check it out!@#','36946828-6696-4b83-be33-6093f2efef70','2025-06-13 13:39:44','active','2025-06-20 13:09:39'),(26,'221c58c0-a00b-4ebc-bfdc-fcca8fc8e80c','lorem ipsum paragraphs 789','ABCDE Lorem ipsum dolor sit, amet consectetur adipisicing elit. \n\nIllum, ea non sint provident aut nobis nesciunt dolor ipsa sequi autem. Neque corrupti reiciendis autem dicta reprehenderit rerum eligendi tempore! Iusto beatae mollitia sint a totam praesentium, deserunt omnis, id maiores quisquam recusandae ipsum tenetur, animi at cumque laborum ab molestias \n\nexplicabo aliquam vel reiciendis rerum quod veniam.\n Perspiciatis vero cumque neque. Inventore doloremque quia nemo sit amet reprehenderit id laboriosam officiis corporis repudiandae! Ipsum voluptas magnam earum qui, numquam sed magni quasi consequatur labore repellendus iusto deleniti voluptates quae soluta optio excepturi fugiat culpa cum! Maxime repudiandae autem maiores sint.','36946828-6696-4b83-be33-6093f2efef70','2025-06-13 13:43:47','active','2025-06-20 13:14:37'),(27,'cc7ce757-5d08-4281-a3e8-b6da71e7d075','lorem ipsum not alice','Lorem ipsum dolor sit amet consectetur adipisicing elit. Quae maxime beatae impedit adipisci quod inventore quia, nulla saepe vel optio officia consequuntur assumenda eveniet recusandae, quo vitae eaque placeat vero veritatis reprehenderit expedita error! Commodi magnam nihil voluptatum iure enim similique deserunt, necessitatibus nulla eveniet dolorem rem est illo impedit animi in, numquam corrupti porro facilis maiores corporis. Mollitia ipsam in quisquam provident non perferendis praesentium a laudantium ullam numquam, nisi dolorum. Magnam at iste ipsum fugiat ea dolore modi consequatur unde temporibus minus, porro eos aut maiores ad numquam, est sit culpa ut iusto quia! Libero rem dolorem et.','aa02e645-55ea-4aa3-953e-3ea543c8290f','2025-06-13 14:00:17','active','2025-06-13 14:00:17');
/*!40000 ALTER TABLE `announcements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructors`
--

DROP TABLE IF EXISTS `instructors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instructors` (
  `instructor_id` int NOT NULL AUTO_INCREMENT,
  `instructor_guid` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `revenue` int NOT NULL DEFAULT '0',
  `user_status` varchar(50) NOT NULL DEFAULT 'created',
  `instructor_name` varchar(100) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`instructor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructors`
--

LOCK TABLES `instructors` WRITE;
/*!40000 ALTER TABLE `instructors` DISABLE KEYS */;
INSERT INTO `instructors` VALUES (4,'36946828-6696-4b83-be33-6093f2efef70','kuandalice@gmail.com',0,'CREATED','Alice Ho','female','The best golf instructor in Taiwan!','1996-08-05');
/*!40000 ALTER TABLE `instructors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lessons`
--

DROP TABLE IF EXISTS `lessons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lessons` (
  `lesson_id` int NOT NULL AUTO_INCREMENT,
  `lesson_guid` varchar(100) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `location_guid` varchar(100) DEFAULT NULL,
  `topic` varchar(100) DEFAULT NULL,
  `instructor_comments` varchar(500) DEFAULT NULL,
  `student_feedback` varchar(500) DEFAULT NULL,
  `lesson_status` varchar(50) NOT NULL DEFAULT 'created',
  `lesson_rating` int NOT NULL DEFAULT '0',
  `student_guid` varchar(100) NOT NULL,
  `instructor_guid` varchar(100) NOT NULL,
  `subject_guid` varchar(100) NOT NULL,
  `transaction_guid` varchar(100) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`lesson_id`),
  UNIQUE KEY `idx_lesson_guid` (`lesson_guid`),
  KEY `fk_lessons_transaction` (`transaction_guid`),
  KEY `fk_lessons_subject` (`subject_guid`),
  KEY `fk_lessons_locationGUID` (`location_guid`),
  CONSTRAINT `fk_lessons_locationGUID` FOREIGN KEY (`location_guid`) REFERENCES `locations` (`location_guid`),
  CONSTRAINT `fk_lessons_subject` FOREIGN KEY (`subject_guid`) REFERENCES `subjects` (`subject_guid`),
  CONSTRAINT `fk_lessons_transaction` FOREIGN KEY (`transaction_guid`) REFERENCES `transactions` (`transaction_guid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lessons`
--

LOCK TABLES `lessons` WRITE;
/*!40000 ALTER TABLE `lessons` DISABLE KEYS */;
INSERT INTO `lessons` VALUES (7,'e6e12cb9-2cba-4d56-8673-d73a065e5cd5','2025-07-17 15:54:00','test1','testTopic',NULL,NULL,'scheduled',0,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','subject-abc-123','e8a22811-57e5-4807-9109-be5a75366079','2025-07-17 16:54:00'),(13,'9ac97f96-892c-46e7-b997-3ac2f42791f5','2025-07-24 18:24:00','test2','testTopic',NULL,NULL,'scheduled',0,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','subject-abc-123','e7942880-cd6c-4c8d-92d6-4cf8dd61e77d','2025-07-24 19:24:00'),(14,'402e4616-6089-43ef-89eb-3464111dbb00','2025-07-25 17:07:00','test1',NULL,NULL,NULL,'scheduled',0,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','subject-abc-123',NULL,'2025-07-25 18:07:00'),(15,'82fa4d43-b5a2-454d-b6f0-43b0a01ceda7','2025-07-26 17:07:00','test1',NULL,NULL,NULL,'scheduled',0,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','subject-abc-123',NULL,'2025-07-26 18:07:00'),(16,'c0d53c6d-9754-401f-9455-9765ffb18ae5','2025-07-27 17:07:00','test1',NULL,NULL,NULL,'scheduled',0,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','subject-abc-123',NULL,'2025-07-27 18:07:00'),(17,'ca73f70e-03ca-4c52-b73d-a0b1838a7ab0','2025-07-28 17:07:00','test1',NULL,NULL,NULL,'scheduled',0,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','subject-abc-123',NULL,'2025-07-28 18:07:00'),(18,'1164c7bf-6554-42b5-9a57-1b466b5edcff','2025-07-29 17:07:00','test1',NULL,NULL,NULL,'scheduled',0,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','subject-abc-123',NULL,'2025-07-29 18:07:00'),(19,'71c58440-fc7b-48a5-9ff3-6b458c0378bd','2025-07-30 17:07:00','test1',NULL,NULL,NULL,'scheduled',0,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','subject-abc-123',NULL,'2025-07-30 18:07:00'),(20,'35eb10a6-518e-4cc3-8334-1c214a4fc49c','2025-07-31 17:07:00','test1',NULL,NULL,NULL,'scheduled',0,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','subject-abc-123',NULL,'2025-07-31 18:07:00'),(21,'2cd88d0e-c9e5-4f75-97dd-4793438c0dbf','2025-08-01 17:07:00','test1',NULL,NULL,NULL,'scheduled',0,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','subject-abc-123',NULL,'2025-08-01 18:07:00'),(22,'b1683827-b6f8-4982-af0b-08e5ced0f04c','2025-08-02 17:07:00','test1',NULL,NULL,NULL,'scheduled',0,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','subject-abc-123',NULL,'2025-08-02 18:07:00'),(23,'2f6ac0e8-180a-4747-a269-4dca42696c8d','2025-08-03 17:07:00','test1',NULL,NULL,NULL,'scheduled',0,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','subject-abc-123',NULL,'2025-08-03 18:07:00'),(24,'f53d69c7-477c-4654-8880-9946eb7412ef','2025-07-31 14:21:00','test1',NULL,NULL,NULL,'scheduled',0,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','8b0ab0ce-7ceb-4c0e-a5b9-ec94fb2bb571',NULL,'2025-07-31 15:21:00');
/*!40000 ALTER TABLE `lessons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `location_id` int NOT NULL AUTO_INCREMENT,
  `location_guid` varchar(100) NOT NULL,
  `user_guid` varchar(100) NOT NULL,
  `location_name` varchar(255) NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  UNIQUE KEY `location_guid` (`location_guid`),
  UNIQUE KEY `uq_user_location_name` (`user_guid`,`location_name`),
  CONSTRAINT `fk_user_guid` FOREIGN KEY (`user_guid`) REFERENCES `users` (`user_guid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,'test1','3b7adf98-c0b1-4fe5-959f-23ba833c74a0','test1-location-name','Taiwan','Taipei','this street','104'),(2,'test2','3b7adf98-c0b1-4fe5-959f-23ba833c74a0','test2-location-name','Taiwan','Taipei','the other street','104'),(5,'90a4f8f6-70a3-4f65-a3c4-a105b662b076','3b7adf98-c0b1-4fe5-959f-23ba833c74a0','zhulun home2','Taiwan','Taipei','7F., No. 52 Zhulun St., Zhongshan Dist','104'),(6,'277d6db6-2c69-42fe-9376-94c51a6fdf98','36946828-6696-4b83-be33-6093f2efef70','My Apartment','Taiwan ','Taipei','1F., No. 5, Ln 308, Section 2, JianGuo S. Rd., Da\'an Dist.','106');
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package`
--

DROP TABLE IF EXISTS `package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package` (
  `package_id` int NOT NULL AUTO_INCREMENT,
  `package_guid` varchar(100) NOT NULL,
  `discount_code` varchar(50) NOT NULL,
  `subject_guid` varchar(100) NOT NULL,
  `number_of_lessons` int NOT NULL,
  `discount_rate` decimal(3,2) NOT NULL,
  PRIMARY KEY (`package_id`),
  UNIQUE KEY `uq_package_guid` (`package_guid`),
  UNIQUE KEY `uq_discountcode_subjectguid` (`discount_code`,`subject_guid`),
  KEY `fk_package_subject` (`subject_guid`),
  CONSTRAINT `fk_package_subject` FOREIGN KEY (`subject_guid`) REFERENCES `subjects` (`subject_guid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chk_discount_rate` CHECK ((`discount_rate` between 0.01 and 1.00))
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package`
--

LOCK TABLES `package` WRITE;
/*!40000 ALTER TABLE `package` DISABLE KEYS */;
INSERT INTO `package` VALUES (1,'package-abc-123','BASIC','subject-abc-123',1,1.00),(6,'db47d7ff-79a1-47fb-b31e-632352669773','TEN','subject-abc-123',10,0.80),(7,'823b25a2-2b7e-4def-8cb6-dc124492616d','BASIC','8b0ab0ce-7ceb-4c0e-a5b9-ec94fb2bb571',1,1.00),(8,'0aaa4f70-99b7-41b8-a8af-bf274442cc8d','HAHAHAH','8b0ab0ce-7ceb-4c0e-a5b9-ec94fb2bb571',5,0.90),(9,'66d08200-9efc-4ccc-91ed-645b51345afa','BASIC','a8a711ff-d5e1-4668-b17d-7bf163bf1920',1,1.00),(10,'806097b4-a522-4283-89f9-ef1bdb239f26','FIVE','a8a711ff-d5e1-4668-b17d-7bf163bf1920',5,0.90);
/*!40000 ALTER TABLE `package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `student_guid` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `student_name` varchar(100) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'created',
  `gender` varchar(10) NOT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `unique_fields` (`student_guid`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (2,'edf7e0b4-55bf-4302-8685-c546a78a05e9','kuantest@example.com','few342','CREATED','MALE','lol','2025-04-23'),(3,'d0fa80c4-9bbe-4b10-acf3-0f00e6bed65a','kuantest23@example.com','Kuantest','CREATED','MALE','fdfawe','2025-04-23'),(4,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','kuantest1234@example.com','Kuantest','CREATED','MALE','fdaerwq','2025-05-12');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjects` (
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(100) NOT NULL,
  `instructor_guid` varchar(100) NOT NULL,
  `price` int NOT NULL,
  `subject_guid` varchar(100) NOT NULL,
  `subject_description` text,
  `duration` int NOT NULL,
  PRIMARY KEY (`subject_id`),
  UNIQUE KEY `subject_name` (`subject_name`,`instructor_guid`),
  UNIQUE KEY `idx_subject_guid` (`subject_guid`),
  KEY `fk_instructor_user` (`instructor_guid`),
  CONSTRAINT `fk_instructor_user` FOREIGN KEY (`instructor_guid`) REFERENCES `users` (`user_guid`),
  CONSTRAINT `chk_duration_positive` CHECK ((`duration` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` VALUES (1,'golf 1-1','36946828-6696-4b83-be33-6093f2efef70',3000,'subject-abc-123','1-1 golf lessons!',60),(4,'English 1-1','36946828-6696-4b83-be33-6093f2efef70',1000,'8b0ab0ce-7ceb-4c0e-a5b9-ec94fb2bb571','LOL',50),(5,'golf 1-2','36946828-6696-4b83-be33-6093f2efef70',3800,'a8a711ff-d5e1-4668-b17d-7bf163bf1920','HAHAHAHA',60);
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscriptions` (
  `subscription_id` int NOT NULL AUTO_INCREMENT,
  `instructor_guid` varchar(100) NOT NULL,
  `student_guid` varchar(100) NOT NULL,
  `subscription_status` varchar(16) NOT NULL DEFAULT 'REQUESTED',
  PRIMARY KEY (`subscription_id`),
  UNIQUE KEY `unique_subscription` (`instructor_guid`,`student_guid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriptions`
--

LOCK TABLES `subscriptions` WRITE;
/*!40000 ALTER TABLE `subscriptions` DISABLE KEYS */;
INSERT INTO `subscriptions` VALUES (18,'36946828-6696-4b83-be33-6093f2efef70','3b7adf98-c0b1-4fe5-959f-23ba833c74a0','REQUESTED');
/*!40000 ALTER TABLE `subscriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `student_guid` varchar(100) DEFAULT NULL,
  `instructor_guid` varchar(100) DEFAULT NULL,
  `transaction_status` varchar(50) NOT NULL DEFAULT 'created',
  `payment_amount` int DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `confirmed_date` datetime DEFAULT NULL,
  `canceled_date` datetime DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `transaction_guid` varchar(100) NOT NULL,
  `subject_guid` varchar(100) DEFAULT NULL,
  `package_guid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  UNIQUE KEY `uq_transactions_transaction_guid` (`transaction_guid`),
  KEY `fk_transactions_subject` (`subject_guid`),
  KEY `fk_transactions_package` (`package_guid`),
  CONSTRAINT `fk_transactions_package` FOREIGN KEY (`package_guid`) REFERENCES `package` (`package_guid`),
  CONSTRAINT `fk_transactions_subject` FOREIGN KEY (`subject_guid`) REFERENCES `subjects` (`subject_guid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (2,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','PENDING',3000,'2025-07-17 15:56:12',NULL,NULL,NULL,'e8a22811-57e5-4807-9109-be5a75366079','subject-abc-123','package-abc-123'),(4,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','PENDING',3000,'2025-07-22 14:25:01',NULL,NULL,NULL,'e7942880-cd6c-4c8d-92d6-4cf8dd61e77d','subject-abc-123','package-abc-123'),(7,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','PENDING',24000,'2025-07-23 17:18:59',NULL,NULL,NULL,'b93d3bca-0577-42c9-bf4b-9730da24d5b5','subject-abc-123','db47d7ff-79a1-47fb-b31e-632352669773'),(8,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','36946828-6696-4b83-be33-6093f2efef70','PENDING',1000,'2025-07-24 14:22:29',NULL,NULL,NULL,'71e2a3d4-1118-45d7-8840-ea61c1e795ce','8b0ab0ce-7ceb-4c0e-a5b9-ec94fb2bb571','823b25a2-2b7e-4def-8cb6-dc124492616d');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_guid` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(500) NOT NULL,
  `ROLE` varchar(100) NOT NULL DEFAULT 'student',
  `status` varchar(100) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `description` text,
  `birthday` datetime DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_guid` (`user_guid`),
  UNIQUE KEY `unique_email_role` (`email`,`ROLE`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (18,'36946828-6696-4b83-be33-6093f2efef70','kuandalice@gmail.com','{bcrypt}$2a$10$cbNuVCFlntaTR6Ugvr1lIu790hHSNnNof9/fAcFcj0G7I/L9ulpUS','INSTRUCTOR','CREATED','Alice Ho','female','The best golf instructor in Taiwan!','1996-08-05 00:00:00','/alice.jpg','2025-04-23 16:10:00','2025-06-10 13:37:39'),(25,'edf7e0b4-55bf-4302-8685-c546a78a05e9','kuantest@example.com','{bcrypt}$2a$10$SR0z9KozjW.9hP/VxSZv9eP.u50.buh0ROsUtYbsdYXRDYTumj0fG','STUDENT','CREATED','few342','MALE','lol','2025-04-23 00:00:00',NULL,'2025-04-23 17:26:35','2025-06-10 13:38:37'),(26,'d0fa80c4-9bbe-4b10-acf3-0f00e6bed65a','kuantest23@example.com','{bcrypt}$2a$10$vkHO5vbvYRazKc1vauoJXOUvDUSUg2zy9BznlfGhCLLH1sbv9aNyy','STUDENT','CREATED','Kuantest','MALE','fdfawe','2025-04-23 00:00:00',NULL,'2025-04-23 17:29:44','2025-06-10 13:38:37'),(27,'3b7adf98-c0b1-4fe5-959f-23ba833c74a0','kuantest1234@example.com','{bcrypt}$2a$10$Q/.eryryfRr0k1Y.NO8JpOZ56Q16ZO4hl5LJoUkT1mxStB4C5CaNS','STUDENT','CREATED','Kuantest','MALE','fdaerwq','2025-05-12 00:00:00','/favicon.ico','2025-05-12 11:47:40','2025-06-10 13:38:37'),(28,'aa02e645-55ea-4aa3-953e-3ea543c8290f','kuantest1234@example.com','{bcrypt}$2a$10$yXfB6jT58O1tIEQuQHMgO.ew1C94JajaYP6PjocSTAkcni48Xk09S','INSTRUCTOR','CREATED','Kuan Chen','MALE','account used for testing','1996-11-05 00:00:00',NULL,'2025-06-13 13:57:31','2025-06-13 13:57:31');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-05  5:40:17

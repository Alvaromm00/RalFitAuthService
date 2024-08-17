-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: personaltrainermanagement
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `body-part`
--

DROP TABLE IF EXISTS `body-part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `body-part` (
  `body_part_id` int NOT NULL COMMENT 'Body part ID',
  `name` varchar(45) DEFAULT NULL COMMENT 'Name of the body part',
  PRIMARY KEY (`body_part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `body-part`
--

LOCK TABLES `body-part` WRITE;
/*!40000 ALTER TABLE `body-part` DISABLE KEYS */;
/*!40000 ALTER TABLE `body-part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise` (
  `exercise_id` int NOT NULL COMMENT 'ID of the exercise',
  `name` varchar(45) NOT NULL COMMENT 'Name of the exercise',
  `body-part-id` int NOT NULL COMMENT 'ID of the body part that the exercise affects',
  PRIMARY KEY (`exercise_id`),
  KEY `fk_exercise_body-part_idx` (`body-part-id`),
  CONSTRAINT `fk_exercise_body-part` FOREIGN KEY (`body-part-id`) REFERENCES `body-part` (`body_part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measure-history`
--

DROP TABLE IF EXISTS `measure-history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `measure-history` (
  `measurement-id` int NOT NULL AUTO_INCREMENT COMMENT 'Measurement ID',
  `user-id` int NOT NULL COMMENT 'User''s ID',
  `exercise-id` int NOT NULL COMMENT 'Exercise''s ID',
  `reps` int DEFAULT NULL COMMENT 'Repetitions of the serie',
  `weight` float DEFAULT NULL COMMENT 'Weight lifted in the serie',
  PRIMARY KEY (`measurement-id`),
  KEY `fk_measure-history_exercise_idx` (`exercise-id`),
  KEY `fk_measure-history_user_idx` (`user-id`),
  CONSTRAINT `fk_measure-history_exercise` FOREIGN KEY (`exercise-id`) REFERENCES `exercise` (`exercise_id`),
  CONSTRAINT `fk_measure-history_user` FOREIGN KEY (`user-id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measure-history`
--

LOCK TABLES `measure-history` WRITE;
/*!40000 ALTER TABLE `measure-history` DISABLE KEYS */;
/*!40000 ALTER TABLE `measure-history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL COMMENT 'ID of the role',
  `role` varchar(45) NOT NULL COMMENT 'Role name',
  `description` varchar(255) NOT NULL COMMENT 'Description of the role',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_UNIQUE` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN','Usuario administrador de la aplicacion'),(2,'CLIENTE','Usuario cliente de la aplicacion');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT 'User''s ID',
  `name` varchar(32) NOT NULL COMMENT 'Name of the user',
  `last_name` varchar(45) DEFAULT NULL COMMENT 'Last name of the user',
  `email` varchar(255) DEFAULT NULL COMMENT 'Email of the user',
  `password` varchar(32) NOT NULL COMMENT 'Password of the user',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date of creation of the user',
  `dni` varchar(9) NOT NULL COMMENT 'DNI of the user',
  `status` varchar(12) NOT NULL COMMENT 'Status of the user, can be active or disabled ',
  `role_id` int NOT NULL COMMENT 'ID of the user''s role',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `dni_UNIQUE` (`dni`),
  KEY `fk_user_role_idx` (`role_id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ADMIN','ADMIN','admin@ralfit.com','0f6e4a1df0cf5ee97c2066953bed21b2','2024-04-06 16:58:33','12345678A','active',1);
INSERT INTO `user` VALUES (2,'TEST','TEST','test@ralfit.com','dcf7fb88d38b9cbc0719c4d47af0b9ca','2024-04-06 16:58:33','87654321Z','active',2);
INSERT INTO `user` VALUES (3,'Charlie','Brown','charlie.brown@example.com','a1d0c6e83f027327d8461063f4ac58a6','2024-03-10 14:15:30','34567890C','active',2);
INSERT INTO `user` VALUES (4,'David','Williams','david.williams@example.com','98f13708210194c475687be6106a3b84','2024-04-25 16:00:15','45678901D','active',2);
INSERT INTO `user` VALUES (5,'Eve','Jones','eve.jones@example.com','8f14e45fceea167a5a36dedd4bea2543','2024-05-05 17:45:00','56789012E','active',2);
INSERT INTO `user` VALUES (6,'Frank','Garcia','frank.garcia@example.com','c4ca4238a0b923820dcc509a6f75849b','2024-06-15 09:30:45','67890123F','active',2);
INSERT INTO `user` VALUES (7,'Grace','Martinez','grace.martinez@example.com','c81e728d9d4c2f636f067f89cc14862c','2024-07-20 11:15:30','78901234G','active',2);
INSERT INTO `user` VALUES (8,'Hank','Rodriguez','hank.rodriguez@example.com','eccbc87e4b5ce2fe28308fd9f2a7baf3','2024-08-10 13:00:15','89012345H','active',2);
INSERT INTO `user` VALUES (9,'Ivy','Martinez','ivy.martinez@example.com','a87ff679a2f3e71d9181a67b7542122c','2024-09-05 14:45:00','90123456I','active',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user-exercise`
--

DROP TABLE IF EXISTS `user-exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user-exercise` (
  `user_id` int NOT NULL COMMENT 'User''s ID',
  `exercise_id` int NOT NULL COMMENT 'Exercise''s ID',
  `max_reps` varchar(45) DEFAULT NULL COMMENT 'Maximum number of repetitions',
  `max_weight` varchar(45) DEFAULT NULL COMMENT 'Maximum weight measurement',
  PRIMARY KEY (`user_id`,`exercise_id`),
  KEY `fk_user-exercise_exercise_idx` (`exercise_id`),
  CONSTRAINT `fk_user-exercise_exercise` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`exercise_id`),
  CONSTRAINT `fk_user-exercise_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user-exercise`
--

LOCK TABLES `user-exercise` WRITE;
/*!40000 ALTER TABLE `user-exercise` DISABLE KEYS */;
/*!40000 ALTER TABLE `user-exercise` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-11 20:22:02

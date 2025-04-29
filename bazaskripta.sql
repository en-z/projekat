-- MySQL dump 10.13  Distrib 9.2.0, for Linux (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `IshodIspita`
--

DROP TABLE IF EXISTS `IshodIspita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `IshodIspita` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `predmet_id` bigint NOT NULL,
  `student_osoba_id` bigint NOT NULL,
  `brojPokusaja` int NOT NULL,
  `bodovi` int NOT NULL,
  `polozen` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_predmet_has_student_student1_idx` (`student_osoba_id`),
  KEY `fk_predmet_has_student_predmet1_idx` (`predmet_id`),
  CONSTRAINT `fk_predmet_has_student_predmet1` FOREIGN KEY (`predmet_id`) REFERENCES `predmet` (`id`),
  CONSTRAINT `fk_predmet_has_student_student1` FOREIGN KEY (`student_osoba_id`) REFERENCES `student` (`osoba_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IshodIspita`
--

LOCK TABLES `IshodIspita` WRITE;
/*!40000 ALTER TABLE `IshodIspita` DISABLE KEYS */;
/*!40000 ALTER TABLE `IshodIspita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adresa`
--

DROP TABLE IF EXISTS `adresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adresa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ulica` varchar(45) NOT NULL,
  `broj` varchar(45) NOT NULL,
  `grad` varchar(45) NOT NULL,
  `drzava` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adresa`
--

LOCK TABLES `adresa` WRITE;
/*!40000 ALTER TABLE `adresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `adresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fakultet`
--

DROP TABLE IF EXISTS `fakultet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fakultet` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  `opis` text NOT NULL,
  `kontakt` varchar(45) NOT NULL,
  `univerzitet_id` bigint NOT NULL,
  `adresa_id` bigint NOT NULL,
  `dekan` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_fakultet_univerzitet1_idx` (`univerzitet_id`),
  KEY `fk_fakultet_adresa1_idx` (`adresa_id`),
  KEY `fk_fakultet_nastavnik1_idx` (`dekan`),
  CONSTRAINT `fk_fakultet_adresa1` FOREIGN KEY (`adresa_id`) REFERENCES `adresa` (`id`),
  CONSTRAINT `fk_fakultet_nastavnik1` FOREIGN KEY (`dekan`) REFERENCES `nastavnik` (`osoba_id`),
  CONSTRAINT `fk_fakultet_univerzitet1` FOREIGN KEY (`univerzitet_id`) REFERENCES `univerzitet` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fakultet`
--

LOCK TABLES `fakultet` WRITE;
/*!40000 ALTER TABLE `fakultet` DISABLE KEYS */;
/*!40000 ALTER TABLE `fakultet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instrumentEvaluacije`
--

DROP TABLE IF EXISTS `instrumentEvaluacije`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instrumentEvaluacije` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tip` varchar(45) NOT NULL,
  `predmet_id` bigint NOT NULL,
  `opis` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_instrumentEvaluacije_predmet1` (`predmet_id`),
  CONSTRAINT `fk_instrumentEvaluacije_predmet1` FOREIGN KEY (`predmet_id`) REFERENCES `predmet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instrumentEvaluacije`
--

LOCK TABLES `instrumentEvaluacije` WRITE;
/*!40000 ALTER TABLE `instrumentEvaluacije` DISABLE KEYS */;
/*!40000 ALTER TABLE `instrumentEvaluacije` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ishod`
--

DROP TABLE IF EXISTS `ishod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ishod` (
  `naziv` varchar(45) NOT NULL,
  `termin` date NOT NULL,
  `silabus_id` bigint NOT NULL,
  PRIMARY KEY (`silabus_id`),
  CONSTRAINT `fk_ishod_silabus1` FOREIGN KEY (`silabus_id`) REFERENCES `silabus` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ishod`
--

LOCK TABLES `ishod` WRITE;
/*!40000 ALTER TABLE `ishod` DISABLE KEYS */;
/*!40000 ALTER TABLE `ishod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nastavniMaterijal`
--

DROP TABLE IF EXISTS `nastavniMaterijal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nastavniMaterijal` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  `opis` varchar(45) NOT NULL,
  `url` varchar(45) DEFAULT NULL,
  `predmet_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_nastavniMaterijal_predmet1` (`predmet_id`),
  CONSTRAINT `fk_nastavniMaterijal_predmet1` FOREIGN KEY (`predmet_id`) REFERENCES `predmet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nastavniMaterijal`
--

LOCK TABLES `nastavniMaterijal` WRITE;
/*!40000 ALTER TABLE `nastavniMaterijal` DISABLE KEYS */;
/*!40000 ALTER TABLE `nastavniMaterijal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nastavnik`
--

DROP TABLE IF EXISTS `nastavnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nastavnik` (
  `status` varchar(45) NOT NULL,
  `osoba_id` bigint NOT NULL,
  PRIMARY KEY (`osoba_id`),
  KEY `fk_nastavnik_osoba1_idx` (`osoba_id`),
  CONSTRAINT `fk_nastavnik_osoba1` FOREIGN KEY (`osoba_id`) REFERENCES `osoba` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nastavnik`
--

LOCK TABLES `nastavnik` WRITE;
/*!40000 ALTER TABLE `nastavnik` DISABLE KEYS */;
/*!40000 ALTER TABLE `nastavnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nastavnik_has_predmet`
--

DROP TABLE IF EXISTS `nastavnik_has_predmet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nastavnik_has_predmet` (
  `nastavnik_osoba_id` bigint NOT NULL,
  `predmet_id` bigint NOT NULL,
  PRIMARY KEY (`nastavnik_osoba_id`,`predmet_id`),
  KEY `fk_nastavnik_has_predmet_predmet1_idx` (`predmet_id`),
  KEY `fk_nastavnik_has_predmet_nastavnik1_idx` (`nastavnik_osoba_id`),
  CONSTRAINT `fk_nastavnik_has_predmet_nastavnik1` FOREIGN KEY (`nastavnik_osoba_id`) REFERENCES `nastavnik` (`osoba_id`),
  CONSTRAINT `fk_nastavnik_has_predmet_predmet1` FOREIGN KEY (`predmet_id`) REFERENCES `predmet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nastavnik_has_predmet`
--

LOCK TABLES `nastavnik_has_predmet` WRITE;
/*!40000 ALTER TABLE `nastavnik_has_predmet` DISABLE KEYS */;
/*!40000 ALTER TABLE `nastavnik_has_predmet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obavjestenje`
--

DROP TABLE IF EXISTS `obavjestenje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `obavjestenje` (
  `idobavjestenje` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  `sadrzaj` text NOT NULL,
  `datum` date NOT NULL,
  `fk_idpredmet` bigint NOT NULL,
  `nastavnik_osoba_id` bigint NOT NULL,
  PRIMARY KEY (`idobavjestenje`,`fk_idpredmet`,`nastavnik_osoba_id`),
  KEY `fk_obavjestenje_predmet1_idx` (`fk_idpredmet`),
  KEY `fk_obavjestenje_nastavnik1_idx` (`nastavnik_osoba_id`),
  CONSTRAINT `fk_obavjestenje_nastavnik1` FOREIGN KEY (`nastavnik_osoba_id`) REFERENCES `nastavnik` (`osoba_id`),
  CONSTRAINT `fk_obavjestenje_predmet1` FOREIGN KEY (`fk_idpredmet`) REFERENCES `predmet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obavjestenje`
--

LOCK TABLES `obavjestenje` WRITE;
/*!40000 ALTER TABLE `obavjestenje` DISABLE KEYS */;
/*!40000 ALTER TABLE `obavjestenje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osoba`
--

DROP TABLE IF EXISTS `osoba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `osoba` (
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `adresa_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_osoba_adresa1_idx` (`adresa_id`),
  KEY `fk_osoba_user1_idx` (`user_id`),
  CONSTRAINT `fk_osoba_adresa1` FOREIGN KEY (`adresa_id`) REFERENCES `adresa` (`id`),
  CONSTRAINT `fk_osoba_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osoba`
--

LOCK TABLES `osoba` WRITE;
/*!40000 ALTER TABLE `osoba` DISABLE KEYS */;
/*!40000 ALTER TABLE `osoba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `predmet`
--

DROP TABLE IF EXISTS `predmet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `predmet` (
  `id` bigint NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `esbp` int NOT NULL,
  `studiskiProgram_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_predmet_studiskiProgram1_idx` (`studiskiProgram_id`),
  CONSTRAINT `fk_predmet_studiskiProgram1` FOREIGN KEY (`studiskiProgram_id`) REFERENCES `studiskiProgram` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predmet`
--

LOCK TABLES `predmet` WRITE;
/*!40000 ALTER TABLE `predmet` DISABLE KEYS */;
/*!40000 ALTER TABLE `predmet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prijavaIspita`
--

DROP TABLE IF EXISTS `prijavaIspita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prijavaIspita` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_osoba_id` bigint NOT NULL,
  `predmet_id` bigint NOT NULL,
  `godina` year NOT NULL,
  `rok` int NOT NULL,
  `datumPrijave` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_student_has_predmet_predmet1_idx` (`predmet_id`),
  KEY `fk_student_has_predmet_student1_idx` (`student_osoba_id`),
  CONSTRAINT `fk_student_has_predmet_predmet1` FOREIGN KEY (`predmet_id`) REFERENCES `predmet` (`id`),
  CONSTRAINT `fk_student_has_predmet_student1` FOREIGN KEY (`student_osoba_id`) REFERENCES `student` (`osoba_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prijavaIspita`
--

LOCK TABLES `prijavaIspita` WRITE;
/*!40000 ALTER TABLE `prijavaIspita` DISABLE KEYS */;
/*!40000 ALTER TABLE `prijavaIspita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silabus`
--

DROP TABLE IF EXISTS `silabus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `silabus` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tekst` text NOT NULL,
  `predmet_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_silabus_predmet1` (`predmet_id`),
  CONSTRAINT `fk_silabus_predmet1` FOREIGN KEY (`predmet_id`) REFERENCES `predmet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silabus`
--

LOCK TABLES `silabus` WRITE;
/*!40000 ALTER TABLE `silabus` DISABLE KEYS */;
/*!40000 ALTER TABLE `silabus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `brojIndeksa` varchar(45) NOT NULL,
  `godinaUpisa` date NOT NULL,
  `prosecnaOcena` float NOT NULL,
  `osvojeniESPB` int NOT NULL,
  `osoba_id` bigint NOT NULL,
  `studiskiProgram_id` bigint NOT NULL,
  PRIMARY KEY (`osoba_id`),
  KEY `fk_student_studiskiProgram1_idx` (`studiskiProgram_id`),
  CONSTRAINT `fk_student_osoba1` FOREIGN KEY (`osoba_id`) REFERENCES `osoba` (`user_id`),
  CONSTRAINT `fk_student_studiskiProgram1` FOREIGN KEY (`studiskiProgram_id`) REFERENCES `studiskiProgram` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studiskiProgram`
--

DROP TABLE IF EXISTS `studiskiProgram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studiskiProgram` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  `opis` text NOT NULL,
  `fakultet_id` bigint NOT NULL,
  `rukovodioc` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_studiskiProgram_fakultet1_idx` (`fakultet_id`),
  KEY `fk_studiskiProgram_nastavnik1_idx` (`rukovodioc`),
  CONSTRAINT `fk_studiskiProgram_fakultet1` FOREIGN KEY (`fakultet_id`) REFERENCES `fakultet` (`id`),
  CONSTRAINT `fk_studiskiProgram_nastavnik1` FOREIGN KEY (`rukovodioc`) REFERENCES `nastavnik` (`osoba_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studiskiProgram`
--

LOCK TABLES `studiskiProgram` WRITE;
/*!40000 ALTER TABLE `studiskiProgram` DISABLE KEYS */;
/*!40000 ALTER TABLE `studiskiProgram` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `univerzitet`
--

DROP TABLE IF EXISTS `univerzitet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `univerzitet` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `kontakt` varchar(45) NOT NULL,
  `opis` text NOT NULL,
  `adresa_id` bigint NOT NULL,
  `nastavnik_osoba_rektor` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_univerzitet_adresa1_idx` (`adresa_id`),
  KEY `fk_univerzitet_nastavnik1_idx` (`nastavnik_osoba_rektor`),
  CONSTRAINT `fk_univerzitet_adresa1` FOREIGN KEY (`adresa_id`) REFERENCES `adresa` (`id`),
  CONSTRAINT `fk_univerzitet_nastavnik1` FOREIGN KEY (`nastavnik_osoba_rektor`) REFERENCES `nastavnik` (`osoba_id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `univerzitet`
--

LOCK TABLES `univerzitet` WRITE;
/*!40000 ALTER TABLE `univerzitet` DISABLE KEYS */;
/*!40000 ALTER TABLE `univerzitet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'marko@gmail.com','$2y$10$WKITv3zFXPViI1dxrCMLl.JXKtrVymQ56uSdRqIpxK4dYZw8R1xv2');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `roles` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`,`roles`),
  CONSTRAINT `fk_users_roles_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,'ROLE_ADMIN'),(1,'ROLE_STUDENT');
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zavrsniRad`
--

DROP TABLE IF EXISTS `zavrsniRad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zavrsniRad` (
  `id` int NOT NULL AUTO_INCREMENT,
  `opis` varchar(45) NOT NULL,
  `naslov` varchar(45) NOT NULL,
  `student_osoba_id` bigint NOT NULL,
  `nastavnik_osoba_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_zavrsniRad_student1_idx` (`student_osoba_id`),
  KEY `fk_zavrsniRad_nastavnik1_idx` (`nastavnik_osoba_id`),
  CONSTRAINT `fk_zavrsniRad_nastavnik1` FOREIGN KEY (`nastavnik_osoba_id`) REFERENCES `nastavnik` (`osoba_id`),
  CONSTRAINT `fk_zavrsniRad_student1` FOREIGN KEY (`student_osoba_id`) REFERENCES `student` (`osoba_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zavrsniRad`
--

LOCK TABLES `zavrsniRad` WRITE;
/*!40000 ALTER TABLE `zavrsniRad` DISABLE KEYS */;
/*!40000 ALTER TABLE `zavrsniRad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zvanje`
--

DROP TABLE IF EXISTS `zvanje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zvanje` (
  `nastavnik_osoba_id` bigint NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  `datumIzbora` date NOT NULL,
  `datumPrestanka` date NOT NULL,
  PRIMARY KEY (`nastavnik_osoba_id`),
  CONSTRAINT `fk_zvanje_nastavnik1` FOREIGN KEY (`nastavnik_osoba_id`) REFERENCES `nastavnik` (`osoba_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zvanje`
--

LOCK TABLES `zvanje` WRITE;
/*!40000 ALTER TABLE `zvanje` DISABLE KEYS */;
/*!40000 ALTER TABLE `zvanje` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-29 17:55:12

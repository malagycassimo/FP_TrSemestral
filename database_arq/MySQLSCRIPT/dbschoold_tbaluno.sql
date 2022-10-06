-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbschoold
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `tbaluno`
--

DROP TABLE IF EXISTS `tbaluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbaluno` (
  `Cod` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `NrBI` varchar(15) NOT NULL,
  `Genero` varchar(30) DEFAULT NULL,
  `DataNascimento` varchar(30) NOT NULL,
  `Telefone` varchar(20) DEFAULT NULL,
  `Telefone1` varchar(20) DEFAULT NULL,
  `Naturalidade` varchar(50) NOT NULL DEFAULT 'Maputo',
  `CategCarta` text NOT NULL,
  `DataMatricula` varchar(50) NOT NULL,
  `Turma` varchar(30) DEFAULT NULL,
  `Foto` text,
  `Funcionario` text NOT NULL,
  PRIMARY KEY (`Cod`),
  UNIQUE KEY `telefone` (`Telefone`),
  UNIQUE KEY `telefone1_UNIQUE` (`Telefone1`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbaluno`
--

LOCK TABLES `tbaluno` WRITE;
/*!40000 ALTER TABLE `tbaluno` DISABLE KEYS */;
INSERT INTO `tbaluno` VALUES (9,'Aluno1','93830037839 h','    Masculino','10/05/2022',' (258) 23 3333 333',' (258) 22 2222 222','Inhambane','C1 - Veiculo Pesado - acima de 3500kg, mas nao superior a 16000kg','Mon May 23 06:57:58 SAST 2022','TurmaD','C:\\Users\\Prashna\\Documents\\@IMAGEM\\h2.png','Malagy Cassimo'),(10,'Aluno2','87336387384 p','    Feminino','17/11/2005',' (258) 84 6272 782',' (258) 87 9274 628','Manica','B1 - Veiculo Ligeiro - abaixo de 3500kg','Mon May 23 08:29:57 SAST 2022','TurmaD','C:\\Users\\Prashna\\Documents\\@IMAGEM\\h1.png','Administrador');
/*!40000 ALTER TABLE `tbaluno` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-06 13:03:38

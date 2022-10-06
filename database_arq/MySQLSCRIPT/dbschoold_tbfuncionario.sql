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
-- Table structure for table `tbfuncionario`
--

DROP TABLE IF EXISTS `tbfuncionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbfuncionario` (
  `Cod` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `NrBI` varchar(13) NOT NULL,
  `Genero` varchar(30) NOT NULL,
  `DataNascimento` text NOT NULL,
  `Telefone` varchar(20) NOT NULL,
  `Telefone1` varchar(20) DEFAULT NULL,
  `Naturalidade` varchar(50) NOT NULL DEFAULT 'Maputo',
  `Cargo` varchar(50) DEFAULT NULL,
  `DataInicio` varchar(50) NOT NULL,
  `Foto` text,
  PRIMARY KEY (`Cod`),
  UNIQUE KEY `Nr_BI_UNIQUE` (`NrBI`),
  UNIQUE KEY `telefone` (`Telefone`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbfuncionario`
--

LOCK TABLES `tbfuncionario` WRITE;
/*!40000 ALTER TABLE `tbfuncionario` DISABLE KEYS */;
INSERT INTO `tbfuncionario` VALUES (5,'Administrador','34564322323 k','    Masculino','12/05/2022',' +258 85 4673 222',' +258 86 8954 332','Maputo','Tecnico(a) IT','Mon May 23 06:40:12 SAST 2022','C:\\Users\\Prashna\\Documents\\@IMAGEM\\fu.png'),(6,'Malagy Cassimo','98399838299 h','    Masculino','22/07/1998',' +258 84 5392 081',' +258 84 9301 291','Maputo','Instrutor(a)','Mon May 23 07:00:30 SAST 2022','C:\\Users\\Prashna\\Documents\\@IMAGEM\\h3.png'),(7,'Edmilson Justino','10204249242 j','    Feminino','04/10/2002',' +258 87 8788 328',' +258 86 0278 262','Manica','Tecnico(a) IT','Mon May 23 07:45:32 SAST 2022','C:\\Users\\Prashna\\Documents\\@IMAGEM\\m1.png'),(8,'Milagre Mabote','10203943490 g','    Masculino','11/05/2022',' +258 87 0928 278',' +258 85 2932 748','Sofala','Seguranca','Mon May 23 07:47:40 SAST 2022','C:\\Users\\Prashna\\Documents\\@IMAGEM\\m1.png');
/*!40000 ALTER TABLE `tbfuncionario` ENABLE KEYS */;
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

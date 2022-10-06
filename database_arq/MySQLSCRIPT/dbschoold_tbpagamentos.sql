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
-- Table structure for table `tbpagamentos`
--

DROP TABLE IF EXISTS `tbpagamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbpagamentos` (
  `Cod` int NOT NULL AUTO_INCREMENT,
  `TipoPagamento` varchar(90) NOT NULL,
  `Aluno` text NOT NULL,
  `IdAluno` varchar(45) DEFAULT NULL,
  `MeioPagamento` varchar(90) NOT NULL,
  `Valor` double NOT NULL,
  `Funcionario` text NOT NULL,
  `DataPagamento` text NOT NULL,
  PRIMARY KEY (`Cod`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPRESSED;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbpagamentos`
--

LOCK TABLES `tbpagamentos` WRITE;
/*!40000 ALTER TABLE `tbpagamentos` DISABLE KEYS */;
INSERT INTO `tbpagamentos` VALUES (8,'Taxa de Exame','Aluno1','9','Mpesa',1000,'Administrador','23 de 4 de 2022'),(9,'Taxa de Exame','Aluno1','9','POS',800,'Administrador','23 de 4 de 2022'),(10,'Venda a Dinheiro','Aluno2','10','Transferencia',2000,'Administrador','23 de 4 de 2022'),(11,'Propina ','Aluno2','10','POS',100,'Administrador','25 de 4 de 2022'),(12,'Propina ','Aluno2','10','Mpesa',1000,'Administrador','25 de 4 de 2022');
/*!40000 ALTER TABLE `tbpagamentos` ENABLE KEYS */;
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

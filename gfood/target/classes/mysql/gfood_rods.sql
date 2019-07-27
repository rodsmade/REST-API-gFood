create database rods;
use rods;
CREATE TABLE `produto` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `nome` varchar(50) NOT NULL,
                           `descricao` text NOT NULL,
                           `preco` decimal(7,2) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `cliente` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `nome` varchar(50) NOT NULL,
                           `endereco` varchar(100) DEFAULT NULL,
                           `nascimento` date NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

INSERT INTO `produto` VALUES (1,'Bolo de maçã','Receita de família. ssssssegredo!',19.00),(2,'Bolo de cenoura','Com cobertura de brigadeiro!!!',19.00),(3,'Bolo de chocolate','Com cobertura de cenoura.',19.99),(4,'Batman Squeezer','300ml. Mantém gelado por 12h.',190.00);

INSERT INTO `cliente` VALUES (2,'Marilia Angela','rua eugenio de freitas nº 255','1994-03-27'),(3,'Gabriel Alexandre','rua dos abacaxis nº 9','1997-05-22'),(4,'Rogners Rogério','rua sem nome nº 1000','1983-08-13'),(5,'Carmem Souza','rua 1 numero 2','1981-02-27'),(6,'Carmem Souza','rua 1 numero 2','1981-02-27'),(7,'Rodolfo Rogério','rua akdsja nº jdhkajsdh','1985-12-17'),(8,'Winicius de Morais','rua adkfasjsfasd nº 5234563','1993-06-14'),(9,'Teste','rua asassss nº 111','1993-06-15');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `nome` varchar(50) NOT NULL,
                           `descricao` text NOT NULL,
                           `preco` decimal(7,2) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Bolo de maçã','Receita de família. ssssssegredo!',19.00),(2,'Bolo de cenoura','Com cobertura de brigadeiro!!!',19.00),(3,'Bolo de chocolate','Com cobertura de cenoura.',19.99),(4,'Batman Squeezer','300ml. Mantém gelado por 12h.',190.00);
INSERT INTO `cliente` VALUES (2,'Marilia Angela','rua eugenio de freitas nº 255','1994-03-27'),(3,'Gabriel Alexandre','rua dos abacaxis nº 9','1997-05-22'),(4,'Rogners Rogério','rua sem nome nº 1000','1983-08-13'),(5,'Carmem Souza','rua 1 numero 2','1981-02-27'),(6,'Carmem Souza','rua 1 numero 2','1981-02-27'),(7,'Rodolfo Rogério','rua akdsja nº jdhkajsdh','1985-12-17'),(8,'Winicius de Morais','rua adkfasjsfasd nº 5234563','1993-06-14'),(9,'Teste','rua asassss nº 111','1993-06-15');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `nome` varchar(50) NOT NULL,
                           `descricao` text NOT NULL,
                           `preco` decimal(7,2) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Bolo de maçã','Receita de família. ssssssegredo!',19.00),(2,'Bolo de cenoura','Com cobertura de brigadeiro!!!',19.00),(3,'Bolo de chocolate','Com cobertura de cenoura.',19.99),(4,'Batman Squeezer','300ml. Mantém gelado por 12h.',190.00);

INSERT INTO `cliente` VALUES (2,'Marilia Angela','rua eugenio de freitas nº 255','1994-03-27'),(3,'Gabriel Alexandre','rua dos abacaxis nº 9','1997-05-22'),(4,'Rogners Rogério','rua sem nome nº 1000','1983-08-13'),(5,'Carmem Souza','rua 1 numero 2','1981-02-27'),(6,'Carmem Souza','rua 1 numero 2','1981-02-27'),(7,'Rodolfo Rogério','rua akdsja nº jdhkajsdh','1985-12-17'),(8,'Winicius de Morais','rua adkfasjsfasd nº 5234563','1993-06-14'),(9,'Teste','rua asassss nº 111','1993-06-15');

SELECT * FROM cliente;
INSERT INTO `cliente` VALUES ( null, "Pablo","rua dois", "1991-01-06");
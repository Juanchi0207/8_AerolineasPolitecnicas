-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.31-0ubuntu0.22.04.1

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
-- Table structure for table `avion`
--

DROP TABLE IF EXISTS `avion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avion` (
  `patente` int NOT NULL,
  `modelo_modelo` varchar(45) NOT NULL,
  `num_serie` int DEFAULT NULL,
  PRIMARY KEY (`patente`),
  KEY `fk_avion_modelo1_idx` (`modelo_modelo`),
  CONSTRAINT `fk_avion_modelo1` FOREIGN KEY (`modelo_modelo`) REFERENCES `modelo` (`modelo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avion`
--

LOCK TABLES `avion` WRITE;
/*!40000 ALTER TABLE `avion` DISABLE KEYS */;
INSERT INTO `avion` VALUES (1,'Boeing 747',123);
/*!40000 ALTER TABLE `avion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idioma`
--

DROP TABLE IF EXISTS `idioma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `idioma` (
  `ididioma` int NOT NULL,
  `idioma` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ididioma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idioma`
--

LOCK TABLES `idioma` WRITE;
/*!40000 ALTER TABLE `idioma` DISABLE KEYS */;
/*!40000 ALTER TABLE `idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idioma_has_tripulante`
--

DROP TABLE IF EXISTS `idioma_has_tripulante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `idioma_has_tripulante` (
  `idioma_ididioma` int NOT NULL,
  `tripulante_persona_dni` int NOT NULL,
  PRIMARY KEY (`idioma_ididioma`,`tripulante_persona_dni`),
  KEY `fk_idioma_has_tripulante_tripulante1_idx` (`tripulante_persona_dni`),
  KEY `fk_idioma_has_tripulante_idioma1_idx` (`idioma_ididioma`),
  CONSTRAINT `fk_idioma_has_tripulante_idioma1` FOREIGN KEY (`idioma_ididioma`) REFERENCES `idioma` (`ididioma`),
  CONSTRAINT `fk_idioma_has_tripulante_tripulante1` FOREIGN KEY (`tripulante_persona_dni`) REFERENCES `tripulante` (`persona_dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idioma_has_tripulante`
--

LOCK TABLES `idioma_has_tripulante` WRITE;
/*!40000 ALTER TABLE `idioma_has_tripulante` DISABLE KEYS */;
/*!40000 ALTER TABLE `idioma_has_tripulante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelo`
--

DROP TABLE IF EXISTS `modelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modelo` (
  `modelo` varchar(45) NOT NULL,
  `cant_pasajeros` int DEFAULT NULL,
  `cant_trip_necesaria` int DEFAULT NULL,
  PRIMARY KEY (`modelo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelo`
--

LOCK TABLES `modelo` WRITE;
/*!40000 ALTER TABLE `modelo` DISABLE KEYS */;
INSERT INTO `modelo` VALUES ('Boeing 747',400,25);
/*!40000 ALTER TABLE `modelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelo_has_tripulante`
--

DROP TABLE IF EXISTS `modelo_has_tripulante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modelo_has_tripulante` (
  `modelo_modelo` varchar(45) NOT NULL,
  `tripulante_persona_dni` int NOT NULL,
  PRIMARY KEY (`modelo_modelo`,`tripulante_persona_dni`),
  KEY `fk_modelo_has_tripulante_tripulante1_idx` (`tripulante_persona_dni`),
  KEY `fk_modelo_has_tripulante_modelo1_idx` (`modelo_modelo`),
  CONSTRAINT `fk_modelo_has_tripulante_modelo1` FOREIGN KEY (`modelo_modelo`) REFERENCES `modelo` (`modelo`),
  CONSTRAINT `fk_modelo_has_tripulante_tripulante1` FOREIGN KEY (`tripulante_persona_dni`) REFERENCES `tripulante` (`persona_dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelo_has_tripulante`
--

LOCK TABLES `modelo_has_tripulante` WRITE;
/*!40000 ALTER TABLE `modelo_has_tripulante` DISABLE KEYS */;
INSERT INTO `modelo_has_tripulante` VALUES ('Boeing 747',46111111),('Boeing 747',46222222);
/*!40000 ALTER TABLE `modelo_has_tripulante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasajero`
--

DROP TABLE IF EXISTS `pasajero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pasajero` (
  `vip` tinyint DEFAULT NULL,
  `necesidades_especiales` tinyint DEFAULT NULL,
  `persona_dni` int NOT NULL,
  PRIMARY KEY (`persona_dni`),
  CONSTRAINT `fk_pasajero_persona` FOREIGN KEY (`persona_dni`) REFERENCES `persona` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasajero`
--

LOCK TABLES `pasajero` WRITE;
/*!40000 ALTER TABLE `pasajero` DISABLE KEYS */;
INSERT INTO `pasajero` VALUES (1,0,46679230),(1,0,46878279);
/*!40000 ALTER TABLE `pasajero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `dni` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (46111111,'Tadeo','Dimeglio','2005-08-20'),(46222222,'Joaquin','Bardelli','2005-10-15'),(46679230,'Ciro','Cerutti','2005-09-30'),(46878279,'Juan','Sampieri','2005-07-02');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sector`
--

DROP TABLE IF EXISTS `sector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sector` (
  `idsector` int NOT NULL,
  `sector` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idsector`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sector`
--

LOCK TABLES `sector` WRITE;
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
INSERT INTO `sector` VALUES (1,'Ala Norte'),(2,'Ala Sur'),(3,'Ala Este'),(4,'Ala Oeste');
/*!40000 ALTER TABLE `sector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tripulante`
--

DROP TABLE IF EXISTS `tripulante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tripulante` (
  `persona_dni` int NOT NULL,
  PRIMARY KEY (`persona_dni`),
  CONSTRAINT `fk_tripulante_persona1` FOREIGN KEY (`persona_dni`) REFERENCES `persona` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tripulante`
--

LOCK TABLES `tripulante` WRITE;
/*!40000 ALTER TABLE `tripulante` DISABLE KEYS */;
INSERT INTO `tripulante` VALUES (46111111),(46222222);
/*!40000 ALTER TABLE `tripulante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tripulante_has_vuelo`
--

DROP TABLE IF EXISTS `tripulante_has_vuelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tripulante_has_vuelo` (
  `tripulante_persona_dni` int NOT NULL,
  `vuelo_idvuelo` int NOT NULL,
  `sector_idsector` int NOT NULL,
  PRIMARY KEY (`tripulante_persona_dni`,`vuelo_idvuelo`),
  KEY `fk_tripulante_has_vuelo_vuelo1_idx` (`vuelo_idvuelo`),
  KEY `fk_tripulante_has_vuelo_tripulante1_idx` (`tripulante_persona_dni`),
  KEY `fk_tripulante_has_vuelo_sector1_idx` (`sector_idsector`),
  CONSTRAINT `fk_tripulante_has_vuelo_sector1` FOREIGN KEY (`sector_idsector`) REFERENCES `sector` (`idsector`),
  CONSTRAINT `fk_tripulante_has_vuelo_tripulante1` FOREIGN KEY (`tripulante_persona_dni`) REFERENCES `tripulante` (`persona_dni`),
  CONSTRAINT `fk_tripulante_has_vuelo_vuelo1` FOREIGN KEY (`vuelo_idvuelo`) REFERENCES `vuelo` (`idvuelo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tripulante_has_vuelo`
--

LOCK TABLES `tripulante_has_vuelo` WRITE;
/*!40000 ALTER TABLE `tripulante_has_vuelo` DISABLE KEYS */;
INSERT INTO `tripulante_has_vuelo` VALUES (46111111,1,1),(46111111,2,1),(46222222,1,1);
/*!40000 ALTER TABLE `tripulante_has_vuelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vuelo`
--

DROP TABLE IF EXISTS `vuelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vuelo` (
  `idvuelo` int NOT NULL,
  `avion_patente1` int NOT NULL,
  `fecha_vuelo` date DEFAULT NULL,
  `origen` varchar(45) DEFAULT NULL,
  `destino` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idvuelo`),
  KEY `fk_vuelo_avion1_idx` (`avion_patente1`),
  CONSTRAINT `fk_vuelo_avion1` FOREIGN KEY (`avion_patente1`) REFERENCES `avion` (`patente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vuelo`
--

LOCK TABLES `vuelo` WRITE;
/*!40000 ALTER TABLE `vuelo` DISABLE KEYS */;
INSERT INTO `vuelo` VALUES (1,1,'2023-06-12','CABA','Miami'),(2,1,'2023-06-16','Miami','CABA'),(3,1,'2023-06-19','CABA','Miami');
/*!40000 ALTER TABLE `vuelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vuelo_has_pasajero`
--

DROP TABLE IF EXISTS `vuelo_has_pasajero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vuelo_has_pasajero` (
  `vuelo_idvuelo` int NOT NULL,
  `pasajero_persona_dni` int NOT NULL,
  PRIMARY KEY (`vuelo_idvuelo`,`pasajero_persona_dni`),
  KEY `fk_vuelo_has_pasajero_pasajero1_idx` (`pasajero_persona_dni`),
  KEY `fk_vuelo_has_pasajero_vuelo1_idx` (`vuelo_idvuelo`),
  CONSTRAINT `fk_vuelo_has_pasajero_pasajero1` FOREIGN KEY (`pasajero_persona_dni`) REFERENCES `pasajero` (`persona_dni`),
  CONSTRAINT `fk_vuelo_has_pasajero_vuelo1` FOREIGN KEY (`vuelo_idvuelo`) REFERENCES `vuelo` (`idvuelo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vuelo_has_pasajero`
--

LOCK TABLES `vuelo_has_pasajero` WRITE;
/*!40000 ALTER TABLE `vuelo_has_pasajero` DISABLE KEYS */;
INSERT INTO `vuelo_has_pasajero` VALUES (1,46679230),(2,46679230),(2,46878279),(3,46878279);
/*!40000 ALTER TABLE `vuelo_has_pasajero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mydb'
--

--
-- Dumping routines for database 'mydb'
--
/*!50003 DROP FUNCTION IF EXISTS `cantVuelosXtripulanteXdia` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`alumno`@`localhost` FUNCTION `cantVuelosXtripulanteXdia`(dniTrip int, fechaAnalizar date) RETURNS int
    DETERMINISTIC
begin
	declare cantVuelos int default 0;
    select count(*) into cantVuelos from vuelo 
    join tripulante_has_vuelo on vuelo.idvuelo=idvuelo
    where tripulante_persona_dni=dniTrip and fecha_vuelo=fechaAnalizar;
    return cantVuelos;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `dniTripMasVuelosXmes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`alumno`@`localhost` FUNCTION `dniTripMasVuelosXmes`() RETURNS int
    DETERMINISTIC
begin
	declare terminar boolean default 0;
    declare dniActual int default null;
    declare dniMasViajes int default null;
    declare cantTemp int default null;
    declare cantMax int default 0;
    declare recorrerTripulantes CURSOR for select persona_dni from tripulante;
    DECLARE CONTINUE HANDLER for not found SET terminar=1;
    open recorrerTripulantes;
    bucle:loop
		fetch recorrerTripulantes into dniActual;
		if terminar=1 then
			leave bucle;
		end if;
        select count(*) into cantTemp from vuelo 
        join tripulante_has_vuelo on vuelo_idvuelo=idvuelo
		where tripulante_persona_dni=dniActual and month(fecha_vuelo)=month(curdate());
        if (cantTemp > cantMax or cantMax=0) then
			set cantMax=cantTemp;
            set dniMasViajes=dniActual;
        end if;
	end loop bucle;
    return dniMasViajes;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `cambioPasaje` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`alumno`@`localhost` PROCEDURE `cambioPasaje`(in idPasajero int, in vueloID int)
begin
declare FV int;
declare idVueloNuevo int;
declare origenNuevo varchar(45);
declare destinoNuevo varchar(45);
select week(fecha_vuelo)+1 into FV from vuelo where idvuelo = vueloID;
select origen into origenNuevo from vuelo where idvuelo=vueloID;
select destino into destinoNuevo from vuelo where idvuelo=vueloID;
select idvuelo into idVueloNuevo from vuelo where week(fecha_vuelo)=FV
and destino=destinoNuevo and origen=origenNuevo;
update vuelo_has_pasajero set vuelo_idvuelo = idVueloNuevo 
where idPasajero = pasajero_persona_dni and vuelo_idvuelo=vueloID; 
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `listarPasajerosXvuelo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`alumno`@`localhost` PROCEDURE `listarPasajerosXvuelo`()
begin
	declare terminar boolean default 0;
    declare codActual int default null;
    declare recorrerVuelos CURSOR for select idvuelo from vuelo;
    DECLARE CONTINUE HANDLER for not found SET terminar=1;
    open recorrerVuelos;
    bucle:loop
		fetch recorrerVuelos into codActual;
		if terminar=1 then
			leave bucle;
		end if;
        select count(*) as cantidad_pasajeros,vuelo_idvuelo as idvuelo from persona 
        join pasajero on dni=persona_dni join vuelo_has_pasajero 
        on persona_dni=pasajero_persona_dni where vuelo_idvuelo=codActual 
        group by vuelo_idvuelo;
	end loop bucle;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `listarTripulantesXvuelo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`alumno`@`localhost` PROCEDURE `listarTripulantesXvuelo`()
begin
	declare terminar boolean default 0;
    declare codActual int default null;
    declare recorrerVuelos CURSOR for select idvuelo from vuelo;
    DECLARE CONTINUE HANDLER for not found SET terminar=1;
    open recorrerVuelos;
    bucle:loop
		fetch recorrerVuelos into codActual;
		if terminar=1 then
			leave bucle;
		end if;
        select count(*) as cantidad_tripulantes,vuelo_idvuelo as idvuelo from persona 
        join tripulante on dni=persona_dni join tripulante_has_vuelo 
        on persona_dni=tripulante_persona_dni where vuelo_idvuelo=codActual
        group by vuelo_idvuelo;
	end loop bucle;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `listarVuelosAutorizadosXtripulante` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`alumno`@`localhost` PROCEDURE `listarVuelosAutorizadosXtripulante`(in dniTrip int)
begin
	select idvuelo, avion_patente1, fecha_vuelo, origen, destino from vuelo join avion on vuelo.avion_patente1=avion.patente 
    join modelo on avion.modelo_modelo=modelo.modelo join modelo_has_tripulante 
    on modelo_has_tripulante.modelo_modelo=modelo.modelo 
    where modelo_has_tripulante.modelo_modelo=avion.modelo_modelo and tripulante_persona_dni=dniTrip;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-14  9:38:27

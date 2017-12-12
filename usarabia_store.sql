-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: usarabia_store
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `BRANCHES`
--

DROP TABLE IF EXISTS `BRANCHES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BRANCHES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `DESCRIPTION` longtext,
  `NAME` varchar(255) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKv3ifrx37ugj7tqr4a45x0x0l` (`CREATED_BY`),
  KEY `FKm4p8afbw3nvsyg61r66y0u78h` (`LAST_UPDATED_BY`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BRANCHES`
--

LOCK TABLES `BRANCHES` WRITE;
/*!40000 ALTER TABLE `BRANCHES` DISABLE KEYS */;
/*!40000 ALTER TABLE `BRANCHES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CACHES`
--

DROP TABLE IF EXISTS `CACHES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CACHES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `QTY` double DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKk1912jpxucl2socn7ykfqi5ot` (`CREATED_BY`),
  KEY `FK7wxqr0b0a8j1ax29mxdyrmfwq` (`LAST_UPDATED_BY`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CACHES`
--

LOCK TABLES `CACHES` WRITE;
/*!40000 ALTER TABLE `CACHES` DISABLE KEYS */;
/*!40000 ALTER TABLE `CACHES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CACHE_MOVEMENTS`
--

DROP TABLE IF EXISTS `CACHE_MOVEMENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CACHE_MOVEMENTS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AMOUNT` double DEFAULT NULL,
  `DATE` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `REF_NUMBER` bigint(20) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `CACHE_ID` int(11) DEFAULT NULL,
  `CLIENT_ID` int(11) DEFAULT NULL,
  `INVENTORY_ID` int(11) DEFAULT NULL,
  `SUPPLIER_ID` int(11) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKb5dls8qhhfkfpekvhrx1jdugp` (`CACHE_ID`),
  KEY `FKarjn4kwbixkj72qic8wunuxuu` (`CLIENT_ID`),
  KEY `FKngy2nvt8ikkfcjoisg9e93im6` (`INVENTORY_ID`),
  KEY `FKm7qnm3ri1suxpsmof6prv5gf6` (`SUPPLIER_ID`),
  KEY `FKl05cc2ufem03vbqwt8f769ejd` (`USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CACHE_MOVEMENTS`
--

LOCK TABLES `CACHE_MOVEMENTS` WRITE;
/*!40000 ALTER TABLE `CACHE_MOVEMENTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `CACHE_MOVEMENTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CLIENTS`
--

DROP TABLE IF EXISTS `CLIENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CLIENTS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `MOBILE1` varchar(255) DEFAULT NULL,
  `MOBILE2` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKfft3gs2tb2v1pb9q836goqqur` (`CREATED_BY`),
  KEY `FK67lxbn0mabnuia4857phj0c35` (`LAST_UPDATED_BY`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CLIENTS`
--

LOCK TABLES `CLIENTS` WRITE;
/*!40000 ALTER TABLE `CLIENTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `CLIENTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FACILITY`
--

DROP TABLE IF EXISTS `FACILITY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FACILITY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `CITY` varchar(255) DEFAULT NULL,
  `GOVERNORATE` varchar(255) DEFAULT NULL,
  `MOBILE1` varchar(255) DEFAULT NULL,
  `MOBILE2` varchar(255) DEFAULT NULL,
  `MORE_INFO` longtext,
  `NAME` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FACILITY`
--

LOCK TABLES `FACILITY` WRITE;
/*!40000 ALTER TABLE `FACILITY` DISABLE KEYS */;
INSERT INTO `FACILITY` VALUES (1,'بركة السبع - البر الغربى','بركة السبع','المنوفية','01063249874','01236987455','البضاعة المباعة لا ترد ولا تستبدل فى حالة مرور 14 يوم','شركة برمجيات','0482992536');
/*!40000 ALTER TABLE `FACILITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INVENTORIES`
--

DROP TABLE IF EXISTS `INVENTORIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `INVENTORIES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `BRANCH_ID` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKgc990p563ksuclm4r4fdr6bnr` (`BRANCH_ID`),
  KEY `FKlda0is62ub00vgee29tlo0lcu` (`CREATED_BY`),
  KEY `FKndxko1dprn2o3fuqa9ko3mcn8` (`LAST_UPDATED_BY`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INVENTORIES`
--

LOCK TABLES `INVENTORIES` WRITE;
/*!40000 ALTER TABLE `INVENTORIES` DISABLE KEYS */;
/*!40000 ALTER TABLE `INVENTORIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ITEMS`
--

DROP TABLE IF EXISTS `ITEMS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ITEMS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(255) DEFAULT NULL,
  `EXPIRATION_DATE` date DEFAULT NULL,
  `HOME` varchar(255) DEFAULT NULL,
  `MAX_LIMIT` int(11) DEFAULT NULL,
  `MIN_LIMIT` int(11) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PRODUCTION_DATE` date DEFAULT NULL,
  `TAX` double DEFAULT NULL,
  `SUBGROUP_ID` int(11) DEFAULT NULL,
  `DESCRIPTION` longtext,
  `CREATED_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) DEFAULT NULL,
  `PURCHASE_PRICE` double DEFAULT NULL,
  `SALE_PRICE` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK37nl0vesb7o4noi28d9m032a4` (`CREATED_BY`),
  KEY `FKfd5hcpkm9v96j1gq8tbhankp6` (`LAST_UPDATED_BY`),
  KEY `FK87jsr34vh4cef52ibuq8c6hv6` (`SUBGROUP_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ITEMS`
--

LOCK TABLES `ITEMS` WRITE;
/*!40000 ALTER TABLE `ITEMS` DISABLE KEYS */;
/*!40000 ALTER TABLE `ITEMS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MAINGROUPS`
--

DROP TABLE IF EXISTS `MAINGROUPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MAINGROUPS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKsco41hcte5y9fabut9ib2gthr` (`CREATED_BY`),
  KEY `FK9cm7qm1xehmvv10e65wi2675w` (`LAST_UPDATED_BY`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MAINGROUPS`
--

LOCK TABLES `MAINGROUPS` WRITE;
/*!40000 ALTER TABLE `MAINGROUPS` DISABLE KEYS */;
/*!40000 ALTER TABLE `MAINGROUPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PAGES`
--

DROP TABLE IF EXISTS `PAGES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PAGES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAGES`
--

LOCK TABLES `PAGES` WRITE;
/*!40000 ALTER TABLE `PAGES` DISABLE KEYS */;
INSERT INTO `PAGES` VALUES (1,'التحويلات بين المخازن','/store-management-system/transfer'),(2,'التقارير','/store-management-system/reports'),(3,'لوحة التحكم','/store-management-system/settings'),(4,'المستخدمين','/store-management-system/users'),(5,'المخازن','/store-management-system/inventories'),(6,'الفروع','/store-management-system/branches'),(7,'تقرير هامش الربح','/store-management-system/profit'),(8,'كشف مديونيات العملاء','/store-management-system/debits'),(9,'كشف حساب حركة الخزنة','/store-management-system/cache-sum'),(10,'حركة الخزنة','/store-management-system/cache-movements'),(11,'الخزائن','/store-management-system/caches'),(12,'العملاء','/store-management-system/clients'),(13,'الموردين','/store-management-system/suppliers'),(14,'وحدات الأصناف','/store-management-system/units'),(15,'مجموعات الأصناف الفرعية','/store-management-system/subgroups'),(16,'مجموعات الأصناف الرئيسية','/store-management-system/groups'),(17,'جرد الأصناف','/store-management-system/item-balance'),(18,'الأصناف','/store-management-system/items'),(19,'متابعة فواتير مرتجع الشراء','/store-management-system/purchases/return-invoices.jsp'),(20,'متابعة فواتير الشراء','/store-management-system/purchases/invoices.jsp'),(21,'فاتورة شراء جديدة','/store-management-system/purchases'),(22,'متابعة فواتير مرتجع البيع','/store-management-system/sales/return-invoices.jsp'),(23,'متابعة فواتير البيع','/store-management-system/sales/invoices.jsp');
/*!40000 ALTER TABLE `PAGES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRIVILEGES`
--

DROP TABLE IF EXISTS `PRIVILEGES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRIVILEGES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DELETE_PRIVILEGE` bit(1) DEFAULT NULL,
  `INSERT_PRIVILEGE` bit(1) DEFAULT NULL,
  `UPDATE_PRIVILEGE` bit(1) DEFAULT NULL,
  `VIEW_PRIVILEGE` bit(1) DEFAULT NULL,
  `PAGE_ID` int(11) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKdidwgx7l1aojk01jbi59pco2n` (`PAGE_ID`),
  KEY `FKebk17my9dj462e6clapo9ruy9` (`ROLE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRIVILEGES`
--

LOCK TABLES `PRIVILEGES` WRITE;
/*!40000 ALTER TABLE `PRIVILEGES` DISABLE KEYS */;
INSERT INTO `PRIVILEGES` VALUES (1,'','','','',23,1),(2,'','','','',22,1),(3,'','','','',21,1),(4,'','','','',20,1),(5,'','','','',19,1),(6,'','','','',18,1),(7,'','','','',17,1),(8,'','','','',16,1),(9,'','','','',15,1),(10,'','','','',14,1),(11,'','','','',13,1),(12,'','','','',12,1),(13,'','','','',11,1),(14,'','','','',10,1),(15,'','','','',9,1),(16,'','','','',8,1),(17,'','','','',7,1),(18,'','','','',6,1),(19,'','','','',5,1),(20,'','','','',4,1),(21,'','','','',3,1),(22,'','','','',2,1),(23,'','','','',1,1);
/*!40000 ALTER TABLE `PRIVILEGES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROFITS`
--

DROP TABLE IF EXISTS `PROFITS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROFITS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRICE` double DEFAULT NULL,
  `PROFIT` double DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `SALES_INVOICE_HEADER_ID` int(11) DEFAULT NULL,
  `INV_DATE` datetime DEFAULT NULL,
  `INVENTORY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK9w1ebkgsmen6jgf0prvmwhjom` (`INVENTORY_ID`),
  KEY `FKrfm8flly7mt9yv0eig3wgefd2` (`ITEM_ID`),
  KEY `FKiv1tp73qktc3r7nxichsrc3v` (`SALES_INVOICE_HEADER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROFITS`
--

LOCK TABLES `PROFITS` WRITE;
/*!40000 ALTER TABLE `PROFITS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PROFITS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PURCHASE_INVOICE_DETAILS`
--

DROP TABLE IF EXISTS `PURCHASE_INVOICE_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PURCHASE_INVOICE_DETAILS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRICE` double DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `PURCHASE_INVOICE_HEADER_ID` int(11) DEFAULT NULL,
  `UNIT_ID` int(11) DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKfupbensur7qntyaxnq0kajqc` (`ITEM_ID`),
  KEY `FKbsexij4daovje0y15saus3o4p` (`PURCHASE_INVOICE_HEADER_ID`),
  KEY `FKj15037pgmrts2pn60xk420bp1` (`UNIT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PURCHASE_INVOICE_DETAILS`
--

LOCK TABLES `PURCHASE_INVOICE_DETAILS` WRITE;
/*!40000 ALTER TABLE `PURCHASE_INVOICE_DETAILS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PURCHASE_INVOICE_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PURCHASE_INVOICE_HEADER`
--

DROP TABLE IF EXISTS `PURCHASE_INVOICE_HEADER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PURCHASE_INVOICE_HEADER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` datetime DEFAULT NULL,
  `DISCOUNT` varchar(255) DEFAULT NULL,
  `FINAL_TOTAL` double DEFAULT NULL,
  `NUMBER` varchar(255) DEFAULT NULL,
  `PAID` double DEFAULT NULL,
  `REMAIN` double DEFAULT NULL,
  `TAX` int(11) DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `CACHE_ID` int(11) DEFAULT NULL,
  `INVENTORY_ID` int(11) DEFAULT NULL,
  `SUPPLIER_ID` int(11) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKa7gewmrq1et593b0w6x84cu86` (`CACHE_ID`),
  KEY `FKexul0t4e504mb91k3n2vrh7ku` (`INVENTORY_ID`),
  KEY `FKc1o5bpsjsriuq08hmdlncj1g5` (`SUPPLIER_ID`),
  KEY `FKqxjuqdnrisgi5magip79gwpv3` (`USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PURCHASE_INVOICE_HEADER`
--

LOCK TABLES `PURCHASE_INVOICE_HEADER` WRITE;
/*!40000 ALTER TABLE `PURCHASE_INVOICE_HEADER` DISABLE KEYS */;
/*!40000 ALTER TABLE `PURCHASE_INVOICE_HEADER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RETURN_PURCHASE_INVOICE_DETAILS`
--

DROP TABLE IF EXISTS `RETURN_PURCHASE_INVOICE_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RETURN_PURCHASE_INVOICE_DETAILS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRICE` double DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `RETURN_PURCHASE_INVOICE_HEADER_ID` int(11) DEFAULT NULL,
  `UNIT_ID` int(11) DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK5hka5m63wqebtirgabrlfopd0` (`ITEM_ID`),
  KEY `FK11dgddju8jd0rw7hur12369jx` (`RETURN_PURCHASE_INVOICE_HEADER_ID`),
  KEY `FKlmxdbp58ymbihqeoefqcqe4ld` (`UNIT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RETURN_PURCHASE_INVOICE_DETAILS`
--

LOCK TABLES `RETURN_PURCHASE_INVOICE_DETAILS` WRITE;
/*!40000 ALTER TABLE `RETURN_PURCHASE_INVOICE_DETAILS` DISABLE KEYS */;
/*!40000 ALTER TABLE `RETURN_PURCHASE_INVOICE_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RETURN_PURCHASE_INVOICE_HEADER`
--

DROP TABLE IF EXISTS `RETURN_PURCHASE_INVOICE_HEADER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RETURN_PURCHASE_INVOICE_HEADER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` datetime DEFAULT NULL,
  `NUMBER` varchar(255) DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  `PURCHASE_INVOICE_ID` int(11) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK522qy20kl4fog9myeermkhpb2` (`PURCHASE_INVOICE_ID`),
  KEY `FK3h4c5lu901k15k3ibxf7ejxvt` (`USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RETURN_PURCHASE_INVOICE_HEADER`
--

LOCK TABLES `RETURN_PURCHASE_INVOICE_HEADER` WRITE;
/*!40000 ALTER TABLE `RETURN_PURCHASE_INVOICE_HEADER` DISABLE KEYS */;
/*!40000 ALTER TABLE `RETURN_PURCHASE_INVOICE_HEADER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RETURN_SALES_INVOICE_DETAILS`
--

DROP TABLE IF EXISTS `RETURN_SALES_INVOICE_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RETURN_SALES_INVOICE_DETAILS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRICE` double DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `RETURN_SALES_INVOICE_HEADER_ID` int(11) DEFAULT NULL,
  `UNIT_ID` int(11) DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKjrd21pi8jpgh9jvqdv5wqqqug` (`ITEM_ID`),
  KEY `FKdx18qb1jskhqnuw12n79tdxu7` (`RETURN_SALES_INVOICE_HEADER_ID`),
  KEY `FK1mbnswynlfkjyxpt0hcvwi3ux` (`UNIT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RETURN_SALES_INVOICE_DETAILS`
--

LOCK TABLES `RETURN_SALES_INVOICE_DETAILS` WRITE;
/*!40000 ALTER TABLE `RETURN_SALES_INVOICE_DETAILS` DISABLE KEYS */;
/*!40000 ALTER TABLE `RETURN_SALES_INVOICE_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RETURN_SALES_INVOICE_HEADER`
--

DROP TABLE IF EXISTS `RETURN_SALES_INVOICE_HEADER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RETURN_SALES_INVOICE_HEADER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` datetime DEFAULT NULL,
  `NUMBER` bigint(20) DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  `SALES_INVOICE_ID` int(11) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKp1mp57us4q0u3glj7hgism51p` (`SALES_INVOICE_ID`),
  KEY `FK3ktxksh5826uemxtsp839bonp` (`USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RETURN_SALES_INVOICE_HEADER`
--

LOCK TABLES `RETURN_SALES_INVOICE_HEADER` WRITE;
/*!40000 ALTER TABLE `RETURN_SALES_INVOICE_HEADER` DISABLE KEYS */;
/*!40000 ALTER TABLE `RETURN_SALES_INVOICE_HEADER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLES`
--

DROP TABLE IF EXISTS `ROLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROLES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLES`
--

LOCK TABLES `ROLES` WRITE;
/*!40000 ALTER TABLE `ROLES` DISABLE KEYS */;
INSERT INTO `ROLES` VALUES (1,'مشرف');
/*!40000 ALTER TABLE `ROLES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SALES_INVOICE_DETAILS`
--

DROP TABLE IF EXISTS `SALES_INVOICE_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SALES_INVOICE_DETAILS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRICE` double DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `SALES_INVOICE_HEADER_ID` int(11) DEFAULT NULL,
  `UNIT_ID` int(11) DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK1s29bp7t1bmau4d10hubookkh` (`ITEM_ID`),
  KEY `FKogkeykjjeh3ccgd6yampp6dkh` (`SALES_INVOICE_HEADER_ID`),
  KEY `FKmb8ured8k30a5n5x6bspfl7nt` (`UNIT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SALES_INVOICE_DETAILS`
--

LOCK TABLES `SALES_INVOICE_DETAILS` WRITE;
/*!40000 ALTER TABLE `SALES_INVOICE_DETAILS` DISABLE KEYS */;
/*!40000 ALTER TABLE `SALES_INVOICE_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SALES_INVOICE_HEADER`
--

DROP TABLE IF EXISTS `SALES_INVOICE_HEADER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SALES_INVOICE_HEADER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` datetime DEFAULT NULL,
  `DISCOUNT` varchar(255) DEFAULT NULL,
  `FINAL_TOTAL` double DEFAULT NULL,
  `NUMBER` bigint(20) DEFAULT NULL,
  `PAID` double DEFAULT NULL,
  `REMAIN` double DEFAULT NULL,
  `TAX` int(11) DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `CACHE_ID` int(11) DEFAULT NULL,
  `CLIENT_ID` int(11) DEFAULT NULL,
  `INVENTORY_ID` int(11) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKk05cbjeqgi87leswf7qshbqnh` (`CACHE_ID`),
  KEY `FKod95qx7uyus0rorkfby453f96` (`CLIENT_ID`),
  KEY `FKd4hlmdj3yqenon6ae03720vr7` (`INVENTORY_ID`),
  KEY `FKi7gk13a6nu883wgekumfejaou` (`USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SALES_INVOICE_HEADER`
--

LOCK TABLES `SALES_INVOICE_HEADER` WRITE;
/*!40000 ALTER TABLE `SALES_INVOICE_HEADER` DISABLE KEYS */;
/*!40000 ALTER TABLE `SALES_INVOICE_HEADER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SUBGROUPS`
--

DROP TABLE IF EXISTS `SUBGROUPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SUBGROUPS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `MAINGROUP_ID` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK5wu9riyjxccafrmagw3565e2e` (`CREATED_BY`),
  KEY `FKrfo1fja4r02x78v14e65g5rgo` (`LAST_UPDATED_BY`),
  KEY `FKj9oc0ae0lguclg5kc9ffcir1c` (`MAINGROUP_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SUBGROUPS`
--

LOCK TABLES `SUBGROUPS` WRITE;
/*!40000 ALTER TABLE `SUBGROUPS` DISABLE KEYS */;
/*!40000 ALTER TABLE `SUBGROUPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SUPPLIERS`
--

DROP TABLE IF EXISTS `SUPPLIERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SUPPLIERS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `MOBILE1` varchar(255) DEFAULT NULL,
  `MOBILE2` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK9st06s5ihqlj0fo006rcbvnhe` (`CREATED_BY`),
  KEY `FK2ccj3twr1tp0xvdwybf46v9xa` (`LAST_UPDATED_BY`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SUPPLIERS`
--

LOCK TABLES `SUPPLIERS` WRITE;
/*!40000 ALTER TABLE `SUPPLIERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `SUPPLIERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TRANSFER_DETAILS`
--

DROP TABLE IF EXISTS `TRANSFER_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRANSFER_DETAILS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRICE` double DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  `TRANSFER_HEADER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK1wie7ytaigl94of833hxlbp5d` (`ITEM_ID`),
  KEY `FKaqo2tl2y0l4tckpefmhvrrnjc` (`TRANSFER_HEADER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TRANSFER_DETAILS`
--

LOCK TABLES `TRANSFER_DETAILS` WRITE;
/*!40000 ALTER TABLE `TRANSFER_DETAILS` DISABLE KEYS */;
/*!40000 ALTER TABLE `TRANSFER_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TRANSFER_HEADER`
--

DROP TABLE IF EXISTS `TRANSFER_HEADER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRANSFER_HEADER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONFIRMATION_DATE` datetime DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `STATUS` bit(1) DEFAULT NULL,
  `TOTAL_PRICE` double DEFAULT NULL,
  `CACHE_FROM` int(11) DEFAULT NULL,
  `CACHE_TO` int(11) DEFAULT NULL,
  `CONFIRMED_BY` int(11) DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `INVENTORY_FROM` int(11) DEFAULT NULL,
  `INVENTORY_TO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKjd08gmhahkfhjn440ff4jkcxl` (`CACHE_FROM`),
  KEY `FKfeff6ho61ekk4ftbx7nmckp9l` (`CACHE_TO`),
  KEY `FKj9nr3agntlwqqurv6l8ij0cjp` (`CONFIRMED_BY`),
  KEY `FK9lpbh5hybapcfhrtjkakgrlpi` (`CREATED_BY`),
  KEY `FK34ytnjhmipjsxfhm331obxnhp` (`INVENTORY_FROM`),
  KEY `FKacxfe8xm9bao0sq761rnqx8s9` (`INVENTORY_TO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TRANSFER_HEADER`
--

LOCK TABLES `TRANSFER_HEADER` WRITE;
/*!40000 ALTER TABLE `TRANSFER_HEADER` DISABLE KEYS */;
/*!40000 ALTER TABLE `TRANSFER_HEADER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UNITS`
--

DROP TABLE IF EXISTS `UNITS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UNITS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKd21pdmfgs14c8ffa2iii85c7w` (`CREATED_BY`),
  KEY `FKocyt4u0foy4ca813302p4x2fj` (`LAST_UPDATED_BY`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UNITS`
--

LOCK TABLES `UNITS` WRITE;
/*!40000 ALTER TABLE `UNITS` DISABLE KEYS */;
INSERT INTO `UNITS` VALUES (1,'قطعة واحدة من الصنف','قطعة','2017-12-12 14:37:25','2017-12-12 14:37:25',2,1,2);
/*!40000 ALTER TABLE `UNITS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  `CACHE_ID` int(11) DEFAULT NULL,
  `INVENTORY_ID` int(11) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_h9seipfdx324ggtgfpwn29ktn` (`NAME`),
  KEY `FKo9eqhifv5b0kgf1xtbvk9gd1y` (`CACHE_ID`),
  KEY `FKogvei204bkwyfe2enow9mftjy` (`CREATED_BY`),
  KEY `FK2g131nbn9w7h6i8ud0tmjam9k` (`INVENTORY_ID`),
  KEY `FKpwmg64rjddbk0wd4as19wsamd` (`LAST_UPDATED_BY`),
  KEY `FK36fs33yh9ge5skjpbxua65lgv` (`ROLE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES (1,'us','us',2,1,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `item_movment`
--

DROP TABLE IF EXISTS `item_movment`;
/*!50001 DROP VIEW IF EXISTS `item_movment`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `item_movment` AS SELECT 
 1 AS `inv_number`,
 1 AS `date`,
 1 AS `inventory_id`,
 1 AS `inventory_name`,
 1 AS `CLIENT_ID`,
 1 AS `NAME`,
 1 AS `CACHE_ID`,
 1 AS `cash_name`,
 1 AS `ITEM_ID`,
 1 AS `maingroup`,
 1 AS `sub_name`,
 1 AS `item_name`,
 1 AS `unit_id`,
 1 AS `unit_name`,
 1 AS `ITEM_QTY`,
 1 AS `MIN_LIMIT`,
 1 AS `MAX_LIMIT`,
 1 AS `HOME`,
 1 AS `CODE`,
 1 AS `PURCHASE_PRICE`,
 1 AS `SALE_PRICE`,
 1 AS `FINAL_TOTAL`,
 1 AS `inv_type`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `profit_margin`
--

DROP TABLE IF EXISTS `profit_margin`;
/*!50001 DROP VIEW IF EXISTS `profit_margin`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `profit_margin` AS SELECT 
 1 AS `NUMBER`,
 1 AS `DATE`,
 1 AS `USER_NAME`,
 1 AS `FINAL_TOTAL`,
 1 AS `inv_type`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `item_movment`
--

/*!50001 DROP VIEW IF EXISTS `item_movment`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `item_movment` AS select `a`.`NUMBER` AS `inv_number`,`a`.`DATE` AS `date`,`a`.`INVENTORY_ID` AS `inventory_id`,`e`.`NAME` AS `inventory_name`,`a`.`CLIENT_ID` AS `CLIENT_ID`,(select `CLIENTS`.`NAME` from `CLIENTS` where (`CLIENTS`.`ID` = `a`.`CLIENT_ID`)) AS `NAME`,`a`.`CACHE_ID` AS `CACHE_ID`,`c`.`NAME` AS `cash_name`,`b`.`ITEM_ID` AS `ITEM_ID`,`h`.`NAME` AS `maingroup`,`g`.`NAME` AS `sub_name`,`f`.`NAME` AS `item_name`,`I`.`ID` AS `unit_id`,`I`.`NAME` AS `unit_name`,((-(1) * `b`.`QTY`) * `I`.`QTY`) AS `ITEM_QTY`,`f`.`MIN_LIMIT` AS `MIN_LIMIT`,`f`.`MAX_LIMIT` AS `MAX_LIMIT`,`f`.`HOME` AS `HOME`,`f`.`CODE` AS `CODE`,`f`.`PURCHASE_PRICE` AS `PURCHASE_PRICE`,`f`.`SALE_PRICE` AS `SALE_PRICE`,`a`.`FINAL_TOTAL` AS `FINAL_TOTAL`,'مبيعات' AS `inv_type` from (((((((`SALES_INVOICE_HEADER` `a` join `SALES_INVOICE_DETAILS` `b`) join `CACHES` `c`) join `INVENTORIES` `e`) join `ITEMS` `f`) join `SUBGROUPS` `g`) join `MAINGROUPS` `h`) join `UNITS` `I`) where ((`a`.`ID` = `b`.`SALES_INVOICE_HEADER_ID`) and (`a`.`CACHE_ID` = `c`.`ID`) and (`a`.`INVENTORY_ID` = `e`.`ID`) and (`b`.`ITEM_ID` = `f`.`ID`) and (`f`.`SUBGROUP_ID` = `g`.`ID`) and (`g`.`MAINGROUP_ID` = `h`.`ID`) and (`b`.`UNIT_ID` = `I`.`ID`)) union all select `a`.`NUMBER` AS `inv_number`,`a`.`DATE` AS `date`,`a`.`INVENTORY_ID` AS `inventory_id`,`e`.`NAME` AS `inventory_name`,`a`.`SUPPLIER_ID` AS `SUPPLIER_ID`,(select `SUPPLIERS`.`NAME` from `SUPPLIERS` where (`SUPPLIERS`.`ID` = `a`.`SUPPLIER_ID`)) AS `NAME`,`a`.`CACHE_ID` AS `CACHE_ID`,`c`.`NAME` AS `cash_name`,`b`.`ITEM_ID` AS `ITEM_ID`,`h`.`NAME` AS `maingroup`,`g`.`NAME` AS `sub_name`,`f`.`NAME` AS `item_name`,`I`.`ID` AS `unit_id`,`I`.`NAME` AS `unit_name`,(`b`.`QTY` * `I`.`QTY`) AS `ITEM_QTY`,`f`.`MIN_LIMIT` AS `MIN_LIMIT`,`f`.`MAX_LIMIT` AS `MAX_LIMIT`,`f`.`HOME` AS `HOME`,`f`.`CODE` AS `CODE`,`f`.`PURCHASE_PRICE` AS `PURCHASE_PRICE`,`f`.`SALE_PRICE` AS `SALE_PRICE`,`a`.`FINAL_TOTAL` AS `FINAL_TOTAL`,'مشتريات' AS `inv_type` from (((((((`PURCHASE_INVOICE_HEADER` `a` join `PURCHASE_INVOICE_DETAILS` `b`) join `CACHES` `c`) join `INVENTORIES` `e`) join `ITEMS` `f`) join `SUBGROUPS` `g`) join `MAINGROUPS` `h`) join `UNITS` `I`) where ((`a`.`ID` = `b`.`PURCHASE_INVOICE_HEADER_ID`) and (`a`.`CACHE_ID` = `c`.`ID`) and (`a`.`INVENTORY_ID` = `e`.`ID`) and (`b`.`ITEM_ID` = `f`.`ID`) and (`f`.`SUBGROUP_ID` = `g`.`ID`) and (`g`.`MAINGROUP_ID` = `h`.`ID`) and (`b`.`UNIT_ID` = `I`.`ID`)) union all select `a`.`NUMBER` AS `inv_number`,`a`.`DATE` AS `date`,`a`.`INVENTORY_ID` AS `inventory_id`,`e`.`NAME` AS `inventory_name`,`a`.`CLIENT_ID` AS `client_id`,(select `CLIENTS`.`NAME` from `CLIENTS` where (`CLIENTS`.`ID` = `a`.`CLIENT_ID`)) AS `NAME`,`a`.`CACHE_ID` AS `CACHE_ID`,`c`.`NAME` AS `cash_name`,`j`.`ITEM_ID` AS `ITEM_ID`,`h`.`NAME` AS `maingroup`,`g`.`NAME` AS `sub_name`,`f`.`NAME` AS `item_name`,`I`.`ID` AS `unit_id`,`I`.`NAME` AS `unit_name`,(`j`.`QTY` * `I`.`QTY`) AS `ITEM_QTY`,`f`.`MIN_LIMIT` AS `MIN_LIMIT`,`f`.`MAX_LIMIT` AS `MAX_LIMIT`,`f`.`HOME` AS `HOME`,`f`.`CODE` AS `CODE`,`f`.`PURCHASE_PRICE` AS `PURCHASE_PRICE`,`f`.`SALE_PRICE` AS `SALE_PRICE`,`k`.`TOTAL` AS `TOTAL`,'مرتجع مبيعات' AS `inv_type` from (((((((((`SALES_INVOICE_HEADER` `a` join `RETURN_SALES_INVOICE_DETAILS` `j`) join `RETURN_SALES_INVOICE_HEADER` `k`) join `SALES_INVOICE_DETAILS` `b`) join `CACHES` `c`) join `INVENTORIES` `e`) join `ITEMS` `f`) join `SUBGROUPS` `g`) join `MAINGROUPS` `h`) join `UNITS` `I`) where ((`a`.`ID` = `b`.`SALES_INVOICE_HEADER_ID`) and (`a`.`CACHE_ID` = `c`.`ID`) and (`k`.`SALES_INVOICE_ID` = `a`.`ID`) and (`k`.`ID` = `j`.`RETURN_SALES_INVOICE_HEADER_ID`) and (`b`.`ITEM_ID` = `j`.`ITEM_ID`) and (`a`.`INVENTORY_ID` = `e`.`ID`) and (`b`.`ITEM_ID` = `f`.`ID`) and (`f`.`SUBGROUP_ID` = `g`.`ID`) and (`g`.`MAINGROUP_ID` = `h`.`ID`) and (`b`.`UNIT_ID` = `I`.`ID`)) union all select `a`.`NUMBER` AS `inv_number`,`a`.`DATE` AS `date`,`a`.`INVENTORY_ID` AS `inventory_id`,`e`.`NAME` AS `inventory_name`,`a`.`SUPPLIER_ID` AS `SUPPLIER_ID`,(select `SUPPLIERS`.`NAME` from `SUPPLIERS` where (`SUPPLIERS`.`ID` = `a`.`SUPPLIER_ID`)) AS `NAME`,`a`.`CACHE_ID` AS `CACHE_ID`,`c`.`NAME` AS `cash_name`,`J`.`ITEM_ID` AS `ITEM_ID`,`h`.`NAME` AS `maingroup`,`g`.`NAME` AS `sub_name`,`f`.`NAME` AS `item_name`,`I`.`ID` AS `unit_id`,`I`.`NAME` AS `unit_name`,((-(1) * `J`.`QTY`) * `I`.`QTY`) AS `ITEM_QTY`,`f`.`MIN_LIMIT` AS `MIN_LIMIT`,`f`.`MAX_LIMIT` AS `MAX_LIMIT`,`f`.`HOME` AS `HOME`,`f`.`CODE` AS `CODE`,`f`.`PURCHASE_PRICE` AS `PURCHASE_PRICE`,`f`.`SALE_PRICE` AS `SALE_PRICE`,`K`.`TOTAL` AS `TOTAL`,'مرتجع مشتريات' AS `inv_type` from (((((((((`PURCHASE_INVOICE_HEADER` `a` join `RETURN_PURCHASE_INVOICE_DETAILS` `J`) join `RETURN_PURCHASE_INVOICE_HEADER` `K`) join `PURCHASE_INVOICE_DETAILS` `b`) join `CACHES` `c`) join `INVENTORIES` `e`) join `ITEMS` `f`) join `SUBGROUPS` `g`) join `MAINGROUPS` `h`) join `UNITS` `I`) where ((`a`.`ID` = `b`.`PURCHASE_INVOICE_HEADER_ID`) and (`a`.`CACHE_ID` = `c`.`ID`) and (`K`.`PURCHASE_INVOICE_ID` = `a`.`ID`) and (`K`.`ID` = `J`.`RETURN_PURCHASE_INVOICE_HEADER_ID`) and (`b`.`ITEM_ID` = `J`.`ITEM_ID`) and (`a`.`INVENTORY_ID` = `e`.`ID`) and (`b`.`ITEM_ID` = `f`.`ID`) and (`f`.`SUBGROUP_ID` = `g`.`ID`) and (`g`.`MAINGROUP_ID` = `h`.`ID`) and (`b`.`UNIT_ID` = `I`.`ID`)) union all select `a`.`ID` AS `inv_number`,`a`.`CREATED_DATE` AS `CREATED_DATE`,`a`.`INVENTORY_FROM` AS `INVENTORY_FROM`,`e`.`NAME` AS `inventory_name`,'' AS `SUPPLIER_ID`,'' AS `NAME`,`a`.`CACHE_FROM` AS `CACHE_FROM`,`c`.`NAME` AS `cash_name`,`b`.`ITEM_ID` AS `ITEM_ID`,`h`.`NAME` AS `maingroup`,`g`.`NAME` AS `sub_name`,`f`.`NAME` AS `item_name`,'' AS `unit_id`,'' AS `unit_name`,(-(1) * `b`.`QTY`) AS `ITEM_QTY`,`f`.`MIN_LIMIT` AS `MIN_LIMIT`,`f`.`MAX_LIMIT` AS `MAX_LIMIT`,`f`.`HOME` AS `HOME`,`f`.`CODE` AS `CODE`,`f`.`PURCHASE_PRICE` AS `PURCHASE_PRICE`,`f`.`SALE_PRICE` AS `SALE_PRICE`,`b`.`TOTAL` AS `TOTAL`,'تحويل صنف من المخزن' AS `inv_type` from ((((((`TRANSFER_HEADER` `a` join `TRANSFER_DETAILS` `b`) join `INVENTORIES` `e`) join `CACHES` `c`) join `ITEMS` `f`) join `SUBGROUPS` `g`) join `MAINGROUPS` `h`) where ((`a`.`ID` = `b`.`TRANSFER_HEADER_ID`) and (`a`.`STATUS` = 1) and (`a`.`INVENTORY_FROM` = `e`.`ID`) and (`a`.`CACHE_FROM` = `c`.`ID`) and (`b`.`ITEM_ID` = `f`.`ID`) and (`f`.`SUBGROUP_ID` = `g`.`ID`) and (`g`.`MAINGROUP_ID` = `h`.`ID`)) union all select `a`.`ID` AS `inv_number`,`a`.`CREATED_DATE` AS `CREATED_DATE`,`a`.`INVENTORY_TO` AS `INVENTORY_TO`,`e`.`NAME` AS `inventory_name`,'' AS `SUPPLIER_ID`,'' AS `NAME`,`a`.`CACHE_TO` AS `CACHE_TO`,`c`.`NAME` AS `cash_name`,`b`.`ITEM_ID` AS `ITEM_ID`,`h`.`NAME` AS `maingroup`,`g`.`NAME` AS `sub_name`,`f`.`NAME` AS `item_name`,'' AS `unit_id`,'' AS `unit_name`,`b`.`QTY` AS `ITEM_QTY`,`f`.`MIN_LIMIT` AS `MIN_LIMIT`,`f`.`MAX_LIMIT` AS `MAX_LIMIT`,`f`.`HOME` AS `HOME`,`f`.`CODE` AS `CODE`,`f`.`PURCHASE_PRICE` AS `PURCHASE_PRICE`,`f`.`SALE_PRICE` AS `SALE_PRICE`,`b`.`TOTAL` AS `TOTAL`,'تحويل صنف الى المخزن' AS `inv_type` from ((((((`TRANSFER_HEADER` `a` join `TRANSFER_DETAILS` `b`) join `INVENTORIES` `e`) join `CACHES` `c`) join `ITEMS` `f`) join `SUBGROUPS` `g`) join `MAINGROUPS` `h`) where ((`a`.`ID` = `b`.`TRANSFER_HEADER_ID`) and (`a`.`STATUS` = 1) and (`a`.`INVENTORY_TO` = `e`.`ID`) and (`a`.`CACHE_TO` = `c`.`ID`) and (`b`.`ITEM_ID` = `f`.`ID`) and (`f`.`SUBGROUP_ID` = `g`.`ID`) and (`g`.`MAINGROUP_ID` = `h`.`ID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `profit_margin`
--

/*!50001 DROP VIEW IF EXISTS `profit_margin`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `profit_margin` AS select `A`.`NUMBER` AS `NUMBER`,`A`.`DATE` AS `DATE`,`U`.`NAME` AS `USER_NAME`,`A`.`FINAL_TOTAL` AS `FINAL_TOTAL`,'فاتورة بيع' AS `inv_type` from (`SALES_INVOICE_HEADER` `A` join `USERS` `U`) where (`A`.`USER_ID` = `U`.`ID`) union all select `A`.`NUMBER` AS `NUMBER`,`A`.`DATE` AS `DATE`,`U`.`NAME` AS `USER_NAME`,(`A`.`FINAL_TOTAL` * -(1)) AS `(A.FINAL_TOTAL * -1)`,'فاتورة شراء' AS `inv_type` from (`PURCHASE_INVOICE_HEADER` `A` join `USERS` `U`) where (`A`.`USER_ID` = `U`.`ID`) union all select `A`.`NUMBER` AS `NUMBER`,`A`.`DATE` AS `DATE`,`U`.`NAME` AS `USER_NAME`,(`A`.`TOTAL` * -(1)) AS `(A.TOTAL * -1)`,'فاتورة مرتجع بيع' AS `inv_type` from (`RETURN_SALES_INVOICE_HEADER` `A` join `USERS` `U`) where (`A`.`USER_ID` = `U`.`ID`) union all select `A`.`NUMBER` AS `NUMBER`,`A`.`DATE` AS `DATE`,`U`.`NAME` AS `USER_NAME`,`A`.`TOTAL` AS `TOTAL`,'فاتورة مرتجع شراء' AS `inv_type` from (`RETURN_PURCHASE_INVOICE_HEADER` `A` join `USERS` `U`) where (`A`.`USER_ID` = `U`.`ID`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-12 15:18:11

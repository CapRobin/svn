-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.82-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema db_database05
--

CREATE DATABASE IF NOT EXISTS db_database05;
USE db_database05;

--
-- Definition of table `tb_book`
--

DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `author` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_book`
--

/*!40000 ALTER TABLE `tb_book` DISABLE KEYS */;
INSERT INTO `tb_book` (`id`,`name`,`price`,`author`) VALUES 
 (1,'asdf',1,'asdfasdf');
/*!40000 ALTER TABLE `tb_book` ENABLE KEYS */;


--
-- Definition of table `tb_goods`
--

DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_goods`
--

/*!40000 ALTER TABLE `tb_goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_goods` ENABLE KEYS */;


--
-- Definition of table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `sex` varchar(45) NOT NULL,
  `photo` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `tel` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_user`
--

/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` (`id`,`username`,`password`,`sex`,`photo`,`email`,`tel`) VALUES 
 (3,'aaa','aaa','男','images/1.gif','aaa@aaa.com','aaa'),
 (4,'ll','ll','女','images/1.gif','55','13255'),
 (5,'mrsoft','111','男','images/1.gif','mr*****@163.com','0431-84978***');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

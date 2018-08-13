/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.5.60-0ubuntu0.14.04.1 : Database - orm-demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`orm-demo` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `orm-demo`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `money` double DEFAULT NULL,
  `tran_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `account` */

insert  into `account`(`id`,`username`,`money`,`tran_time`) values 
(1,'张三',-2100,'2018-06-26 08:07:14'),
(2,'李四',4100,'2018-06-26 08:07:37');

/*Table structure for table `book_info` */

DROP TABLE IF EXISTS `book_info`;

CREATE TABLE `book_info` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_author` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `book_price` double DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `book_info` */

insert  into `book_info`(`book_id`,`book_author`,`book_price`) values 
(1,'lisi1',112.3),
(3,'张三',212.1),
(4,'张三',212.1),
(5,'张三',212.1),
(6,'张三',212.1),
(7,'张三',212.1),
(8,'张三',212.1),
(9,'张三',212.1),
(10,'张三',212.1),
(11,'张三',212.1),
(12,'张三',212.1),
(13,'张三',212.1),
(14,'张三',212.1),
(15,'张三',212.1),
(16,'张三',212.1),
(17,'张三',212.1),
(18,'张三',212.1),
(19,'张三',212.1),
(20,'jojx',222121.3),
(21,NULL,22.3),
(22,NULL,22.3),
(23,'语文',22.9),
(24,'语文',22.9),
(25,'语文',22.9),
(26,'语文',22.9),
(27,'语文',22.9);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

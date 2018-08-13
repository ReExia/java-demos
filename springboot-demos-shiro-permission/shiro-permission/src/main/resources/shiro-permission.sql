/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.5.60-0ubuntu0.14.04.1 : Database - shiro-permission
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shiro-permission` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `shiro-permission`;

/*Table structure for table `t_customer` */

DROP TABLE IF EXISTS `t_customer`;

CREATE TABLE `t_customer` (
  `id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `customer_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `age` int(11) NOT NULL,
  `gender` int(11) NOT NULL,
  `tel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `qq` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `wechat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `input_time` datetime NOT NULL,
  `seller_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `input_user_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `job_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `salary_level_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `state` int(11) NOT NULL,
  `customer_source_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_customer` */

insert  into `t_customer`(`id`,`customer_name`,`age`,`gender`,`tel`,`email`,`qq`,`wechat`,`input_time`,`seller_id`,`input_user_id`,`job_id`,`salary_level_id`,`state`,`customer_source_id`) values 
('11','张翠山',58,0,'13600899800','zcs@qq.com','68686862','zcs68686862','2015-08-09 00:00:00','2','28','5','17',0,'8'),
('24','张飞',40,0,'15892292891','zf@qq.com','68686864','zf68686863','2015-12-09 00:00:00','2','28','5','17',0,'6'),
('31','赵云',26,0,'15892492891','zy@163.com','68686866','zy68686866','2015-12-06 00:00:00','1','2','1','2',2,'6'),
('36','诸葛亮',36,1,'15892592891','zgl@163.com','68686867','zgl68686867','2015-12-06 00:00:00','11','1','5','7',2,'6'),
('39','东方bb',18,1,'15892692891','dfbb@168.com','68686868','dfbb68686868','2015-12-07 00:00:00','1','1','5','7',1,'8'),
('44','岳不群',16,1,'15892792891','qbq@qq.com','68686810','ybq68686810','2015-12-08 00:00:00','1','1','5','7',-1,'8'),
('8','张无忌',18,0,'13211112222','zwj@qq.com','68686860','zwj68686860','2015-10-11 00:00:00','2','28','13','17',0,'8'),
('82','林冲',36,1,'15892992891','lc@qq.com','68686812','lc68686812','2015-12-08 00:00:00','2','1','5','7',-1,'6'),
('83','宋江',56,1,'15892092891','sj@qq.com','68686813','sj68686813','2015-12-08 00:00:00','2','1','5','7',1,'8'),
('85','曹操',43,1,'15892192891','cz@qq.com','68686815','cz68686815','2015-12-08 00:00:00','1','1','5','7',-1,'8'),
('86','孙权',28,1,'15892193891','sq@qq.com','68686816','sq68686816','2015-12-08 00:00:00','1','1','5','7',2,'8'),
('9','赵敏',20,0,'15223467654','zhaomin@qq.com','68686861','zm68686861','2015-01-26 00:00:00','11','28','5','15',0,'8');

/*Table structure for table `t_department` */

DROP TABLE IF EXISTS `t_department`;

CREATE TABLE `t_department` (
  `id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `sn` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `department_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dir_path` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state` int(20) NOT NULL,
  `manager_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `parent_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_department` */

insert  into `t_department`(`id`,`sn`,`department_name`,`dir_path`,`state`,`manager_id`,`parent_id`) values 
('12','dept12','测试部','/1',-1,'1','1'),
('18','dept18','运维部','/1',-1,'1','1'),
('21','dept21','需求部','/2/22221',0,'2','2'),
('22','dept22','行政部','/1',-1,'1','1'),
('24','dept24','财务部','/1/12/16/17',-1,'1','17');

/*Table structure for table `t_employee` */

DROP TABLE IF EXISTS `t_employee`;

CREATE TABLE `t_employee` (
  `id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `employee_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `real_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `input_time` datetime NOT NULL,
  `state` int(11) NOT NULL,
  `department_id` char(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_employee` */

insert  into `t_employee`(`id`,`employee_name`,`password`,`real_name`,`tel`,`email`,`input_time`,`state`,`department_id`) values 
('1','admin','62efb964427b82a243c4fb11c628f986','张三','100001','zs@laozheng.kt','2016-08-02 00:00:00',0,NULL),
('10','admin10','ed91500191005f7843190aa1a2b814ad','张君宝','1000010','zjb@laozheng.kt','2016-08-02 00:00:00',0,NULL),
('11','admin11','8ad812c7e52a6c8811c0da9bd479fd7b','张烈','1000011','zl@laozheng.kt','2016-08-02 00:00:00',0,NULL),
('12','admin12','8b7c045a3c23077628f271c3cbb1ebd9','张小凡','1000012','zxf@laozheng.kt','2016-08-02 00:00:00',-1,NULL),
('13','admin13','524bc6ec0eaf058a4519673c0784bf13','张丹峰','1000013','zdf@laozheng.kt','2016-08-02 00:00:00',0,NULL),
('14','admin14','2adaf983baec9ff7803ebc1742c95cd3','张麻子','1000014','zmz@laozheng.kt','2016-08-02 00:00:00',0,NULL),
('15','admin15','a64758997a209ff82b74898602a5302c','张召重','1000015','zzz@laozheng.kt','2016-08-02 00:00:00',0,NULL),
('16','admin16','756dcf9e930ddb9db02dfc9c9b0f7afe','张召忠','1000016','zcz@laozheng.kt','2016-08-02 00:00:00',0,NULL),
('17','admin17','0a681f4c71eb5864ca698a2164354a72','王重阳','1000017','wcy@laozheng.kt','2016-08-02 00:00:00',0,NULL),
('18','admin18','0fa2bc07008fa375bcb8249597ae2b93','李寻欢','1000018','lxh@laozheng.kt','2016-08-02 00:00:00',0,NULL),
('2','admin2','e9f128d79283d54b1eb25f23b1c17b5a','张山丰','100002','zsf@laozheng.kt','2016-08-02 00:00:00',0,NULL),
('4','admin4','63eac0108bbcfd82f5a8ed69e7299496','张翼德','100004','zyd@laozheng.kt','2016-08-02 00:00:00',0,NULL),
('5','admin5','1c7406e47313a524a9efb0cc3c4700ed','张飞','100005','zf@laozheng.kt','2016-08-02 00:00:00',-1,NULL),
('6','admin6','940d91aa62e73615e7e9a30b7c058060','张二娃','100006','zew@laozheng.kt','2016-08-02 00:00:00',-1,NULL),
('8','admin8','51016840c4310d71afe84974f567f82e','张翠山','100008','zcs@laozheng.kt','2016-08-02 00:00:00',-1,NULL),
('9','admin9','2c5178244be630742a35b3f851495f6f','张无忌','100009','zwj@laozheng.kt','2016-08-02 00:00:00',0,NULL);

/*Table structure for table `t_employee_role` */

DROP TABLE IF EXISTS `t_employee_role`;

CREATE TABLE `t_employee_role` (
  `id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `employee_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_employee_role` */

insert  into `t_employee_role`(`id`,`employee_id`,`role_id`) values 
('1','1','1'),
('2','1','11'),
('3','11','11');

/*Table structure for table `t_login_info` */

DROP TABLE IF EXISTS `t_login_info`;

CREATE TABLE `t_login_info` (
  `id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_type` bigint(20) NOT NULL,
  `state` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_login_info` */

insert  into `t_login_info`(`id`,`username`,`password`,`user_type`,`state`,`email`,`url`) values 
('1','test','123456a',1,0,NULL,NULL),
('2','xiaoxiao','123456',1,0,NULL,NULL),
('3','xx','123456',1,0,NULL,NULL),
('4','sdf','123456',1,0,NULL,NULL),
('5','123','123456',1,0,NULL,NULL);

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `sn` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `intro` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `parent_id` char(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_menu` */

insert  into `t_menu`(`id`,`sn`,`menu_name`,`icon`,`url`,`intro`,`parent_id`) values 
('1','1','系统管理','icon-add',NULL,NULL,NULL),
('2','2','用户管理','icon-add','/employee/index',NULL,'1'),
('3','3','部门管理','icon-add','/department/index',NULL,'1'),
('4','4','高级业务','icon-add',NULL,NULL,NULL),
('5','5','客户管理','icon-add','/customer/index',NULL,'4'),
('6','6','角色管理','icon-add','/role/index',NULL,'1'),
('7','7','权限管理','icon-add','/permission/index',NULL,'1');

/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
  `id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `permission_id` char(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `resource` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `menu_id` char(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sn` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_permission` */

insert  into `t_permission`(`id`,`permission_id`,`resource`,`state`,`menu_id`,`sn`) values 
('10','客户管理','/customer/index',0,'5','customer:index'),
('11','角色管理','/role/index',0,'6','role:list'),
('12','权限管理','/permission/index',0,'7','permission:list'),
('2','员工管理','/employee/index',0,'2','employee:index'),
('3','部门管理','/department/index',0,'3','department:index'),
('4','员工列表','/employee/list',0,NULL,'employee:list'),
('5','员工删除','/employee/delete',0,NULL,'employee:del'),
('6','员工保存','/employee/save',0,NULL,'employee:save'),
('7','部门列表','/department/list',0,NULL,'department:list'),
('8','部门删除','/department/delete',0,NULL,'department:del'),
('9','部门保存','/department/save',0,NULL,'department:save');

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `role_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sn` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`role_name`,`sn`) values 
('1','超级管理员','admin'),
('11','测试人员','test'),
('3','系统管理员','systemadmin'),
('9','普通管理员','normadmin');

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
  `id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `permission_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_role_permission` */

insert  into `t_role_permission`(`id`,`role_id`,`permission_id`) values 
('1','1','2'),
('10','1','11'),
('11','1','12'),
('12','2','2'),
('13','2','3'),
('14','2','4'),
('15','9','2'),
('16','9','4'),
('17','9','5'),
('18','9','6'),
('19','11','2'),
('2','1','3'),
('20','11','3'),
('21','11','4'),
('22','11','6'),
('23','11','7'),
('24','11','10'),
('3','1','4'),
('4','1','5'),
('5','1','6'),
('6','1','7'),
('7','1','8'),
('8','1','9'),
('9','1','10');

/*Table structure for table `t_system_dictionary` */

DROP TABLE IF EXISTS `t_system_dictionary`;

CREATE TABLE `t_system_dictionary` (
  `id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `sn` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `system_dictionary_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `intro` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_system_dictionary` */

insert  into `t_system_dictionary`(`id`,`sn`,`system_dictionary_name`,`intro`,`state`) values 
('1','product','xxx','aaa1',1),
('2','product','产品单位','单位',1),
('3','product','产品规格','规格',1),
('4','salaryLevel','收入水平','金额',1),
('5','customerSource','客户来源','客户来源',1),
('6','job','职业','职业',1),
('8','product','搜索','事实上',1);

/*Table structure for table `t_system_dictionary_item` */

DROP TABLE IF EXISTS `t_system_dictionary_item`;

CREATE TABLE `t_system_dictionary_item` (
  `id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `parent_id` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `system_dictionary_item_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `requence` int(11) NOT NULL,
  `intro` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_system_dictionary_item` */

insert  into `t_system_dictionary_item`(`id`,`parent_id`,`system_dictionary_item_name`,`requence`,`intro`) values 
('1','2','Nike',1,'耐克'),
('2','3','ADDISS',2,'阿迪达斯'),
('3','2','LiNing',3,'李宁'),
('4','3','361度',4,'361'),
('5','6','鸿星尔克',5,'鸿星尔克'),
('6','5','双星',6,'双星');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

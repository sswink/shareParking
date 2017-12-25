/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.68-community : Database - sharepl
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sharepl` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sharepl`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `admin_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) NOT NULL,
  `admin_password` char(16) NOT NULL,
  `parking_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '如果parking_id为0，证明该管理员是总管理员',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`admin_id`,`admin_name`,`admin_password`,`parking_id`) values (1,'admin','admin123',0),(2,'666','mima',2),(3,'456','wode',6),(4,'9','9',9);

/*Table structure for table `generate` */

DROP TABLE IF EXISTS `generate`;

CREATE TABLE `generate` (
  `generate_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `parkinglot_id` bigint(11) NOT NULL COMMENT '停车位ID',
  `generate_share_begin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '共享开始时间',
  `generate_share_end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '共享结束时间',
  `generate_price` decimal(5,2) NOT NULL COMMENT '价格',
  `generate_status` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '状态(0:正在共享 1：已预约 2：已完成 3：已结束 4：已取消)',
  PRIMARY KEY (`generate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='订单表';

/*Data for the table `generate` */

insert  into `generate`(`generate_id`,`parkinglot_id`,`generate_share_begin`,`generate_share_end`,`generate_price`,`generate_status`) values (7,2,'2017-10-01 12:49:00','2017-09-20 20:36:36','20.00','1'),(8,5,'2017-10-01 12:49:59','2017-09-16 19:40:10','1.00','1'),(9,3,'2017-10-01 12:49:47','2017-09-04 23:36:36','30.00','0'),(10,5,'2017-10-01 12:50:00','2017-09-16 17:40:10','30.00','1'),(11,5,'2017-10-01 12:49:44','2017-09-16 14:40:10','20.00','0'),(12,6,'2017-10-01 12:49:43','2017-09-18 20:16:06','3.00','0'),(22,10,'2017-10-01 14:44:00','2017-10-01 19:44:00','99.00','0'),(23,14,'2017-10-01 15:03:00','2017-10-01 20:03:00','98.00','0'),(24,12,'2017-10-01 15:51:00','2017-10-01 20:51:00','68.00','0');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `order_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `user_id` bigint(11) NOT NULL COMMENT '用户ID',
  `generate_id` bigint(11) DEFAULT NULL COMMENT '停车位ID',
  `order_parking_begin` varchar(30) DEFAULT '2000-01-01 00:00:00' COMMENT '停车开始时间',
  `order_parking_end` varchar(30) DEFAULT '2000-01-01 00:00:00' COMMENT '停车结束时间',
  `order_parking_totaltime` decimal(5,2) DEFAULT '0.00' COMMENT '停车总时间',
  `order_price` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '收费=价格*总时间',
  `order_status` char(1) NOT NULL COMMENT '状态(0:正在使用 1：已完成 2：已取消  3：已过期)',
  `order_createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='结束订单表';

/*Data for the table `order` */

insert  into `order`(`order_id`,`user_id`,`generate_id`,`order_parking_begin`,`order_parking_end`,`order_parking_totaltime`,`order_price`,`order_status`,`order_createtime`) values (7,17,10,NULL,NULL,NULL,'0.00','0','2017-09-16 15:40:10'),(8,13,7,NULL,NULL,NULL,'0.00','0','2017-09-27 14:19:28'),(9,12,8,NULL,NULL,NULL,'0.00','0','2017-09-27 14:20:23'),(12,17,23,'2000-01-01 00:00:00','2000-01-01 00:00:00','0.00','0.00','0','2017-10-01 15:51:58');

/*Table structure for table `parking` */

DROP TABLE IF EXISTS `parking`;

CREATE TABLE `parking` (
  `parking_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '停车场ID',
  `parking_name` varchar(20) NOT NULL COMMENT '停车位名字',
  `parking_location` varchar(50) NOT NULL COMMENT '停车场地址',
  `longitude` decimal(16,10) NOT NULL COMMENT '经度',
  `latitude` decimal(16,10) NOT NULL COMMENT '纬度',
  `remaker` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`parking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='停车场';

/*Data for the table `parking` */

insert  into `parking`(`parking_id`,`parking_name`,`parking_location`,`longitude`,`latitude`,`remaker`) values (1,'停车场1','某','113.4629382610','23.1262415045',NULL),(2,'停车场2','某某','113.2644939423','23.2261477710',NULL),(3,'停车场3','某某某','113.2649230957','23.1258517699',NULL),(4,'停车场4','某某某某','113.2835229826','23.1267545712',NULL),(5,'停车场5','某某某某某','113.2655882835','23.1269815039',NULL),(6,'停车场6','某某某某某某','113.2655194740','23.1325665362',NULL),(7,'珠江国际大厦','某某某某某某木木','113.2675194740','23.1295665362',NULL),(8,'某某某','某某某','114.2675194740','24.1295665362',NULL),(9,'广东省政府','广东省广州市','113.2662700000','23.1317100000',NULL);

/*Table structure for table `parkinglot` */

DROP TABLE IF EXISTS `parkinglot`;

CREATE TABLE `parkinglot` (
  `parkinglot_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '停车位ID',
  `parking_id` bigint(11) NOT NULL COMMENT '停车场ID',
  `parkinglot_num` varchar(20) NOT NULL COMMENT '停车场内的停车位置',
  `user_id` bigint(11) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`parkinglot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='车位表';

/*Data for the table `parkinglot` */

insert  into `parkinglot`(`parkinglot_id`,`parking_id`,`parkinglot_num`,`user_id`) values (2,9,'粤5',5),(3,8,'粤7',7),(5,9,'粤5',5),(6,9,'粤3',3),(8,2,'粤SSS',12),(10,6,'粤A-6666',17),(12,8,'粤A-6666',17),(14,7,'粤A-6666',17),(15,7,'粤A-6666',17);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `wx_id` varchar(50) NOT NULL COMMENT '微信openid',
  `wx_name` varchar(30) NOT NULL COMMENT '微信名',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `sex` char(1) NOT NULL COMMENT '性别',
  `phone_num` varchar(11) NOT NULL COMMENT '手机号',
  `plate_num` varchar(8) NOT NULL COMMENT '车牌号',
  `regist_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `lastlogin_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`user_id`),
  KEY `idx_phone_num` (`phone_num`),
  KEY `idx_user_name` (`user_name`),
  KEY `idx_plate_num` (`plate_num`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `users` */

insert  into `users`(`user_id`,`wx_id`,`wx_name`,`user_name`,`sex`,`phone_num`,`plate_num`,`regist_time`,`lastlogin_time`) values (3,'32111','大','诶我去二群','男','111','粤3','2017-09-14 18:39:52','2017-09-17 20:38:46'),(5,'999999','qqqq去','daw打算请问王企鹅','男','13454312','粤5','2017-09-03 20:28:28','2017-09-17 20:41:24'),(6,'8888','qqqq','格','男','1345555','粤6','2017-09-03 20:29:05','2017-09-17 22:00:09'),(7,'3213','王企鹅无群大','大打算','男','321312','粤7','2017-09-14 18:39:21','2017-09-17 20:47:07'),(12,'666','恒','恒哥','男','6666666','粤SSS','2017-09-17 22:31:49','2017-09-17 22:31:49'),(13,'23','恒12','的','男','1233','321','2017-09-19 19:34:13','2017-09-19 19:34:12'),(17,'oZSId0TQ0jtpv0GDU2F4G9YvNUpo','张子恒','张子恒','0','18028660208','粤A-6666','2017-09-24 17:05:45','2017-10-02 16:37:48');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

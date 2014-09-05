/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50522
Source Host           : localhost:3306
Source Database       : police_db

Target Server Type    : MYSQL
Target Server Version : 50522
File Encoding         : 65001

Date: 2014-09-05 13:45:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `escapedpeopletbl`
-- ----------------------------
DROP TABLE IF EXISTS `escapedpeopletbl`;
CREATE TABLE `escapedpeopletbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `crimerecord` varchar(100) DEFAULT NULL,
  `idno` varchar(20) DEFAULT NULL,
  `pic` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of escapedpeopletbl
-- ----------------------------

-- ----------------------------
-- Table structure for `uploadfiletbl`
-- ----------------------------
DROP TABLE IF EXISTS `uploadfiletbl`;
CREATE TABLE `uploadfiletbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uploadTime` varchar(50) DEFAULT NULL,
  `fileDesc` varchar(100) DEFAULT NULL,
  `filePath` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uploadfiletbl
-- ----------------------------

-- ----------------------------
-- Table structure for `usertbl`
-- ----------------------------
DROP TABLE IF EXISTS `usertbl`;
CREATE TABLE `usertbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usertbl
-- ----------------------------
INSERT INTO `usertbl` VALUES ('1', 'admin', 'admin');
INSERT INTO `usertbl` VALUES ('2', 'yfr', '123456');

-- ----------------------------
-- Table structure for `vehicleinfotbl`
-- ----------------------------
DROP TABLE IF EXISTS `vehicleinfotbl`;
CREATE TABLE `vehicleinfotbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `idno` varchar(20) DEFAULT NULL,
  `license` varchar(20) DEFAULT NULL,
  `createTime` varchar(50) DEFAULT NULL,
  `faultRecord` varchar(300) DEFAULT NULL,
  `penalty` double(50,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vehicleinfotbl
-- ----------------------------

/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50522
Source Host           : localhost:3306
Source Database       : steel

Target Server Type    : MYSQL
Target Server Version : 50522
File Encoding         : 65001

Date: 2014-09-05 13:41:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL,
  `realName` varchar(20) NOT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `contacts` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'yufarong', 'yfr', '123456', 'nan', '22', 'fage', '15109513995', '5681377');

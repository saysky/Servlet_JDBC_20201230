/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : db_ArcsoftHotel

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 30/12/2020 21:26:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for consume
-- ----------------------------
DROP TABLE IF EXISTS `consume`;
CREATE TABLE `consume` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `service_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roomprice` varchar(45) DEFAULT NULL,
  `roomid` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of consume
-- ----------------------------
BEGIN;
INSERT INTO `consume` VALUES (2, 'null', '149', NULL, NULL, NULL);
INSERT INTO `consume` VALUES (3, 'chace', '227', NULL, NULL, NULL);
INSERT INTO `consume` VALUES (4, 'jhl', '104', NULL, NULL, NULL);
INSERT INTO `consume` VALUES (8, 'xiaojun', '117', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `userId` varchar(45) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `documentnumber` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `face` mediumblob,
  `checkin_date` date DEFAULT NULL,
  `checkout_date` date DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of User
-- ----------------------------
BEGIN;
INSERT INTO `User` VALUES ('aaa', 'aaa', 'aaa', '18268169541', '33333033333033333', NULL, NULL, NULL);
INSERT INTO `User` VALUES ('bbb', 'bbb', 'bbb', '18268169542', '33333033333033333', NULL, NULL, NULL);
INSERT INTO `User` VALUES ('chace', 'chace123', 'zhuyihan', '18268169541', '1111', 0x494D475F333036382E4A5047, NULL, NULL);
INSERT INTO `User` VALUES ('jhl', 'jhljhl', '蒋汉琳', '18268169541', '222222222222221212', NULL, '2020-12-07', '2020-12-25');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

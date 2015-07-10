/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : personnel_department

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2014-06-17 16:18:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dname_unq` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'Accounting');
INSERT INTO `department` VALUES ('2', 'Development');
INSERT INTO `department` VALUES ('3', 'Human Resources');

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `passport_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `birthday` date NOT NULL,
  `salary` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `position_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `position_fk` (`position_id`),
  KEY `employee_fk` (`department_id`),
  CONSTRAINT `employee_fk` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `position_fk` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('7', '94758', 'Katya', '1994-06-04', '3000', '1', '1');
INSERT INTO `employee` VALUES ('8', '7463847', 'Masha', '1980-02-03', '6000', '1', '2');
INSERT INTO `employee` VALUES ('9', '1463847', 'Sveta', '1989-10-16', '3500', '1', '1');
INSERT INTO `employee` VALUES ('10', '7494759', 'Roman', '1994-12-03', '8000', '2', '4');
INSERT INTO `employee` VALUES ('12', '474847484', 'Vasya', '1990-05-01', '3500', '2', '3');
INSERT INTO `employee` VALUES ('13', '83649', 'Leonid', '1994-07-15', '5000', '2', '4');
INSERT INTO `employee` VALUES ('15', '837638', 'Nikita', '1992-01-12', '5000', '3', '5');
INSERT INTO `employee` VALUES ('16', '37487', 'Elena', '1989-01-01', '6000', '3', '6');

-- ----------------------------
-- Table structure for `position`
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `min_salary` int(11) NOT NULL,
  `max_salary` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pname_unq` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('1', 'Accounter', '2000', '4000');
INSERT INTO `position` VALUES ('2', 'Chief accounter', '4500', '6500');
INSERT INTO `position` VALUES ('3', 'Engineer', '3000', '5000');
INSERT INTO `position` VALUES ('4', 'Senior engineer', '4500', '8000');
INSERT INTO `position` VALUES ('5', 'HR manager', '3000', '5000');
INSERT INTO `position` VALUES ('6', 'PR manager', '4000', '6000');

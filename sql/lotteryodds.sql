/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : lotteryodds

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-07-24 18:38:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for lotteryodds
-- ----------------------------
DROP TABLE IF EXISTS `lotteryodds`;
CREATE TABLE `lotteryodds` (
  `id` varchar(255) NOT NULL,
  `content` text,
  `hwOdd` varchar(255) DEFAULT '' COMMENT '不让球主胜赔率',
  `hdOdd` varchar(255) DEFAULT '' COMMENT '不让球主平赔率',
  `hlOdd` varchar(255) DEFAULT '' COMMENT '不让球主负赔率',
  `rhwOdd` varchar(255) DEFAULT NULL COMMENT '让球主胜赔率',
  `rhdOdd` varchar(255) DEFAULT NULL COMMENT '让球主平赔率',
  `rhlOdd` varchar(255) DEFAULT NULL COMMENT '让球主负赔率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lotteryodds
-- ----------------------------

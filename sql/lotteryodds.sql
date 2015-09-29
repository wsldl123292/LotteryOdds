/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : lotteryodds

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-09-29 17:56:41
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
-- Table structure for lotteryoddsnew
-- ----------------------------
DROP TABLE IF EXISTS `lotteryoddsnew`;
CREATE TABLE `lotteryoddsnew` (
  `matchId` varchar(255) NOT NULL DEFAULT '' COMMENT '比赛id',
  `result` int(3) DEFAULT '2' COMMENT '比赛结果 0--负 1--平 3--胜 2--未知',
  `zscore` int(11) DEFAULT '0' COMMENT '主队进球',
  `kscore` int(11) DEFAULT '0' COMMENT '客队进球',
  `cwOddAm` varchar(10) DEFAULT '0.00' COMMENT '初始主胜赔率澳门',
  `cdOddAm` varchar(10) DEFAULT '0.00' COMMENT '初始主平赔率澳门',
  `clOddAm` varchar(10) DEFAULT '0.00' COMMENT '初始主负赔率澳门',
  `lwOddAm` varchar(10) DEFAULT '0.00' COMMENT '最终主胜赔率澳门',
  `ldOddAm` varchar(10) DEFAULT '0.00' COMMENT '最终主平赔率澳门',
  `llOddAm` varchar(10) DEFAULT '0.00' COMMENT '最终主负赔率澳门',
  `cwKlAm` varchar(10) DEFAULT '0.00' COMMENT '初始主胜凯利指数澳门',
  `cdKlAm` varchar(10) DEFAULT '0.00' COMMENT '初始主平凯利指数澳门',
  `clKlAm` varchar(10) DEFAULT '0.00' COMMENT '初始主负凯利指数澳门',
  `lwKlAm` varchar(10) DEFAULT '0.00' COMMENT '最终主胜凯利指数澳门',
  `ldKlAm` varchar(10) DEFAULT '0.00' COMMENT '最终主平凯利指数澳门',
  `llKlAm` varchar(10) DEFAULT '0.00' COMMENT '最终主负凯利指数澳门',
  `cwOddWl` varchar(10) DEFAULT '0.00' COMMENT '初始主胜赔率威廉希尔',
  `cdOddWl` varchar(10) DEFAULT '0.00' COMMENT '初始主平赔率威廉希尔',
  `clOddWl` varchar(10) DEFAULT '0.00' COMMENT '初始主负赔率威廉希尔',
  `lwOddWl` varchar(10) DEFAULT '0.00' COMMENT '最终主胜赔率威廉希尔',
  `ldOddWl` varchar(10) DEFAULT '0.00' COMMENT '最终主平赔率威廉希尔',
  `llOddWl` varchar(10) DEFAULT '0.00' COMMENT '最终主负赔率威廉希尔',
  `cwKlWl` varchar(10) DEFAULT '0.00' COMMENT '初始主胜凯利指数威廉希尔',
  `cdKlWl` varchar(10) DEFAULT '0.00' COMMENT '初始主平凯利指数威廉希尔',
  `clKlWl` varchar(10) DEFAULT '0.00' COMMENT '初始主负凯利指数威廉希尔',
  `lwKlWl` varchar(10) DEFAULT '0.00' COMMENT '最终主胜凯利指数威廉希尔',
  `ldKlWl` varchar(10) DEFAULT '0.00' COMMENT '最终主平凯利指数威廉希尔',
  `llKlWl` varchar(10) DEFAULT '0.00' COMMENT '最终主负凯利指数威廉希尔',
  `cwOddLb` varchar(10) DEFAULT '0.00' COMMENT '初始主胜赔率立博',
  `cdOddLb` varchar(10) DEFAULT '0.00' COMMENT '初始主平赔率立博',
  `clOddLb` varchar(10) DEFAULT '0.00' COMMENT '初始主负赔率立博',
  `lwOddLb` varchar(10) DEFAULT '0.00' COMMENT '最终主胜赔率立博',
  `ldOddLb` varchar(10) DEFAULT '0.00' COMMENT '最终主平赔率立博',
  `llOddLb` varchar(10) DEFAULT '0.00' COMMENT '最终主负赔率立博',
  `cwKlLb` varchar(10) DEFAULT '0.00' COMMENT '初始主胜凯利指数立博',
  `cdKlLb` varchar(10) DEFAULT '0.00' COMMENT '初始主平凯利指数立博',
  `clKlLb` varchar(10) DEFAULT '0.00' COMMENT '初始主负凯利指数立博',
  `lwKlLb` varchar(10) DEFAULT '0.00' COMMENT '最终主胜凯利指数立博',
  `ldKlLb` varchar(10) DEFAULT '0.00' COMMENT '最终主平凯利指数立博',
  `llKlLb` varchar(10) DEFAULT '0.00' COMMENT '最终主负凯利指数立博',
  `scwOdd` varchar(10) DEFAULT '0.00' COMMENT '初始平均主胜赔率',
  `scdOdd` varchar(10) DEFAULT '0.00' COMMENT '初始平均主平赔率',
  `sclOdd` varchar(10) DEFAULT '0.00' COMMENT '初始平均主负赔率',
  `slwOdd` varchar(10) DEFAULT '0.00' COMMENT '最终平均主胜赔率',
  `sldOdd` varchar(10) DEFAULT '0.00' COMMENT '最终平均主平赔率',
  `sllOdd` varchar(10) DEFAULT '0.00' COMMENT '最终平均主负赔率',
  `scwKl` varchar(10) DEFAULT '0.00' COMMENT '初始平均主胜凯利指数',
  `scdKl` varchar(10) DEFAULT '0.00' COMMENT '初始平均主平凯利指数',
  `sclKl` varchar(10) DEFAULT '0.00' COMMENT '初始平均主负凯利指数',
  `slwKl` varchar(10) DEFAULT '0.00' COMMENT '最终平均主胜凯利指数',
  `sldKl` varchar(10) DEFAULT '0.00' COMMENT '最终平均主平凯利指数',
  `sllKl` varchar(10) DEFAULT '0.00' COMMENT '最终平均主负凯利指数',
  `czWaterAm` varchar(10) DEFAULT '0.00' COMMENT '初始主水位澳门',
  `cpAm` varchar(10) DEFAULT '0.00' COMMENT '初始盘口澳门',
  `ckWaterAm` varchar(10) DEFAULT '0.00' COMMENT '初始客水位澳门',
  `lzWaterAm` varchar(10) DEFAULT '0.00' COMMENT '最终主水位澳门',
  `lpAm` varchar(10) DEFAULT '0.00' COMMENT '最终盘口澳门',
  `lkWaterAm` varchar(10) DEFAULT '0.00' COMMENT '最终客水位澳门',
  `czWaterLb` varchar(10) DEFAULT '0.00' COMMENT '初始主水位立博',
  `cpLb` varchar(10) DEFAULT '0.00' COMMENT '初始盘口立博',
  `ckWaterLb` varchar(10) DEFAULT '0.00' COMMENT '初始客水位立博',
  `lzWaterLb` varchar(10) DEFAULT '0.00' COMMENT '最终主水位立博',
  `lpLb` varchar(10) DEFAULT '0.00' COMMENT '最终盘口立博',
  `lkWaterLb` varchar(10) DEFAULT '0.00' COMMENT '最终客水位立博',
  `sczWater` varchar(10) DEFAULT '0.00' COMMENT '平均初始主水位',
  `scp` varchar(10) DEFAULT '0.00' COMMENT '平均初始盘口',
  `sckWater` varchar(10) DEFAULT '0.00' COMMENT '平均初始客水位',
  `slzWater` varchar(10) DEFAULT '0.00' COMMENT '平均最终主水位',
  `slp` varchar(10) DEFAULT '0.00' COMMENT '平均最终盘口',
  `slkWater` varchar(10) DEFAULT '0.00' COMMENT '平均最终客水位',
  `date` varchar(255) DEFAULT NULL COMMENT '比赛时间',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11456 DEFAULT CHARSET=utf8;

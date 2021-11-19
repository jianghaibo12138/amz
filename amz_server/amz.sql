/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1@14006
 Source Server Type    : MySQL
 Source Server Version : 100331
 Source Host           : 127.0.0.1:14006
 Source Schema         : amz

 Target Server Type    : MySQL
 Target Server Version : 100331
 File Encoding         : 65001

 Date: 19/11/2021 19:21:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for amz_TCC
-- ----------------------------
DROP TABLE IF EXISTS `amz_TCC`;
CREATE TABLE `amz_TCC` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tx_id` bigint(20) NOT NULL COMMENT '事务ID',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '事务分支执行URL',
  `branch_type` tinyint(4) DEFAULT NULL COMMENT '事务分支类型',
  `body_data` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '事务分支执行URL携带数据',
  `finish_at` timestamp(6) NULL DEFAULT NULL COMMENT '事务分支完成时间',
  `rollback_at` timestamp(6) NULL DEFAULT NULL COMMENT '事务分支回滚时间',
  `create_at` timestamp(6) NOT NULL DEFAULT current_timestamp(6) COMMENT '事务分支创建时间',
  `update_at` timestamp(6) NULL DEFAULT NULL ON UPDATE current_timestamp(6) COMMENT '事务分支更新时间',
  `branch_status` tinyint(1) DEFAULT NULL COMMENT '事务分支状态',
  `executor` int(11) DEFAULT NULL COMMENT '事务分支执行线程',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;

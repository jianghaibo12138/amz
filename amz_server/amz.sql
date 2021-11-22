/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1@3306
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 127.0.0.1:3306
 Source Schema         : amz

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 19/11/2021 22:26:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for amz_TCC
-- ----------------------------
DROP TABLE IF EXISTS `amz_TCC`;
CREATE TABLE `amz_TCC` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tx_id` bigint NOT NULL COMMENT '事务ID',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '事务分支执行URL',
  `branch_type` tinyint DEFAULT NULL COMMENT '事务分支类型',
  `body_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '事务分支执行URL携带数据',
  `finish_at` timestamp(6) NULL DEFAULT NULL COMMENT '事务分支完成时间',
  `rollback_at` timestamp(6) NULL DEFAULT NULL COMMENT '事务分支回滚时间',
  `create_at` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '事务分支创建时间',
  `update_at` timestamp(6) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '事务分支更新时间',
  `branch_status` tinyint(1) DEFAULT NULL COMMENT '事务分支状态',
  `executor` int DEFAULT NULL COMMENT '事务分支执行线程',
  `header` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `tried_times` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;

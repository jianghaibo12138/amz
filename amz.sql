/*
 Navicat Premium Data Transfer

 Source Server         : local@14006
 Source Server Type    : MySQL
 Source Server Version : 100331
 Source Host           : 127.0.0.1:14006
 Source Schema         : amz

 Target Server Type    : MySQL
 Target Server Version : 100331
 File Encoding         : 65001

 Date: 12/11/2021 13:49:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for TCC
-- ----------------------------
DROP TABLE IF EXISTS `TCC`;
CREATE TABLE `TCC` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tx_id` bigint(20) NOT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `branch_type` tinyint(4) DEFAULT NULL,
  `body_data` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `finish_at` timestamp(6) NULL DEFAULT NULL,
  `rollback_at` timestamp(6) NULL DEFAULT NULL,
  `create_at` timestamp(6) NOT NULL DEFAULT current_timestamp(6),
  `update_at` timestamp(6) NULL DEFAULT NULL ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;

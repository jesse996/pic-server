-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.26 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 导出  表 pic.carousel 结构
CREATE TABLE IF NOT EXISTS `carousel` (
                                          `id` bigint NOT NULL AUTO_INCREMENT,
                                          `url` varchar(255) DEFAULT NULL COMMENT '图片url',
                                          `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                          `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                          `deleted` tinyint DEFAULT '0',
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='轮播图表';

-- 数据导出被取消选择。

-- 导出  表 pic.news 结构
CREATE TABLE IF NOT EXISTS `news` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      `cover_img` varchar(300) DEFAULT NULL COMMENT '封面图',
                                      `title` varchar(100) DEFAULT NULL COMMENT '标题',
                                      `content` text COMMENT '内容',
                                      `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                      `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                      `deleted` tinyint DEFAULT '0',
                                      `description` varchar(255) DEFAULT NULL COMMENT '描述',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='轮播图表';

-- 数据导出被取消选择。

-- 导出  表 pic.pic 结构
CREATE TABLE IF NOT EXISTS `pic` (
                                     `id` bigint NOT NULL AUTO_INCREMENT,
                                     `cover_img` varchar(100) DEFAULT NULL COMMENT '封面图',
                                     `title` varchar(100) DEFAULT NULL COMMENT '标题',
                                     `description` text COMMENT '描述',
                                     `src` varchar(255) DEFAULT NULL COMMENT '来源',
                                     `name` varchar(50) DEFAULT NULL COMMENT '名字',
                                     `type` tinyint NOT NULL DEFAULT '0' COMMENT '0:三次元，1：二次元，2：cosplay',
                                     `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                     `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                     `img_list` json DEFAULT NULL COMMENT '图片列表',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 数据导出被取消选择。

-- 导出  表 pic.pic_tag 结构
CREATE TABLE IF NOT EXISTS `pic_tag` (
                                         `id` bigint NOT NULL AUTO_INCREMENT,
                                         `pic_id` bigint NOT NULL,
                                         `tag_id` bigint NOT NULL,
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='pic_tag表';

-- 数据导出被取消选择。

-- 导出  表 pic.tag 结构
CREATE TABLE IF NOT EXISTS `tag` (
                                     `id` bigint NOT NULL AUTO_INCREMENT,
                                     `name` varchar(50) NOT NULL COMMENT 'tag名',
                                     `cover_img` varchar(255) DEFAULT NULL COMMENT '封面图',
                                     `type` tinyint DEFAULT '0' COMMENT '0:类别，1：专辑',
                                     `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                     `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                     `deleted` tinyint DEFAULT '0',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签表';

-- 数据导出被取消选择。

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

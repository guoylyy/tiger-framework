-- -----------------------------------------------------
-- Schema tiger
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tiger
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fuckcode` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `fuckcode` ;


# Dump of table account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(11) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `user_name` varchar(32) NOT NULL,
  `password` varchar(128) DEFAULT NULL COMMENT '??',
  `gender` varchar(16) DEFAULT NULL COMMENT '0 false FEMALE\n1 true  MALE',
  `birthday` date DEFAULT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `id_card` varchar(32) DEFAULT NULL COMMENT '????',
  `business_scope` varchar(512) DEFAULT NULL COMMENT '????\n',
  `address` varchar(128) DEFAULT NULL COMMENT '??',
  `status` varchar(16) DEFAULT NULL COMMENT '????',
  `ext_params` varchar(4096) DEFAULT NULL COMMENT '????\n',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;

INSERT INTO `account` (`id`, `company_id`, `create_time`, `update_time`, `user_name`, `password`, `gender`, `birthday`, `icon`, `id_card`, `business_scope`, `address`, `status`, `ext_params`)
VALUES
	(1,-1,'2015-09-12 21:54:37','2015-09-12 21:54:37','admin','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table account_login_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_login_log`;

CREATE TABLE `account_login_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(32) DEFAULT NULL,
  `plat` varchar(32) DEFAULT NULL COMMENT 'enums\nweb\nios\nandroid',
  `token` varchar(128) DEFAULT NULL,
  `expire` bigint(11) DEFAULT NULL COMMENT '????',
  `account_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




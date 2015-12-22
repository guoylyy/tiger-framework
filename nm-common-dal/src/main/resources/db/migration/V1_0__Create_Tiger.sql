-- -----------------------------------------------------
-- Schema tiger
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tiger
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nevermore_dev` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `nevermore_dev` ;


-- 以下是基础表，基础表主要涉及到一些比较通用的业务，核心系统业务无关
-- 用户表
CREATE TABLE `account` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `account` varchar(32) NOT NULL,
  `password` varchar(128) DEFAULT NULL COMMENT 'password',
  `name` varchar(256) DEFAULT NULL COMMENT 'name',
  `account` varchar(16) DEFAULT NULL,
  `gender` varchar(16) DEFAULT NULL COMMENT '0 false FEMALE\n1 true  MALE',
  `icon` varchar(64) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL COMMENT 'user status',
  `ext_params` varchar(4096) DEFAULT NULL COMMENT 'ext params',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `account_UNIQUE` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;

-- 用户登录记录表
CREATE TABLE `account_login_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(128) DEFAULT NULL,
  `device` varchar(256) DEFAULT NULL COMMENT 'enums\nweb\nios\nandroid',
  `token` varchar(128) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `expire_time` timestamp NULL DEFAULT NULL COMMENT '',
  `account_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;

-- 用户角色关联表
CREATE TABLE `account_role` (
  `account_id` bigint(11) NOT NULL,
  `role_id` bigint(11) NOT NULL,
  PRIMARY KEY (`account_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 角色
CREATE TABLE `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT 'Teacher/Student/Administrator',
  `remark` varchar(128) DEFAULT NULL COMMENT 'remark',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

-- 用户角色
CREATE TABLE `role_permission` (
  `role_id` bigint(11) NOT NULL,
  `permission_id` bigint(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 权限表
CREATE TABLE `permission` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `operation` varchar(32) DEFAULT NULL,
  `system` varchar(32) DEFAULT NULL COMMENT 'system',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

-- 附件表
CREATE TABLE `attach` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `attach_type` varchar(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `meta_data` text COMMENT 'json',
  `account_id` bigint(11) DEFAULT NULL,
  `is_del` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `url_UNIQUE` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

-- 附件关联表
CREATE TABLE `attach_relate` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(11) DEFAULT NULL,
  `attach_id` bigint(11) DEFAULT NULL,
  `biz_type` varchar(32) DEFAULT NULL,
  `ext_params` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

-- 消息表
CREATE TABLE `message` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `receiver_id` bigint(11) NOT NULL COMMENT '???id',
  `sender_id` bigint(11) NOT NULL COMMENT '???id',
  `is_read` tinyint(4) DEFAULT '0',
  `is_archived` tinyint(4) DEFAULT '0',
  `is_deleted` tinyint(4) DEFAULT '0',
  `title` varchar(64) DEFAULT NULL,
  `description` varchar(64) DEFAULT NULL,
  `content` text COMMENT '消息内容',
  `biz_id` bigint(11) DEFAULT NULL COMMENT '业务id',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `receiver_id_index` (`receiver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

-- 系统参数表
CREATE TABLE `system_params` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `param_name` varchar(64) NOT NULL COMMENT '参数名',
  `param_value` varchar(4096) DEFAULT NULL COMMENT '参数值',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `is_active` tinyint(4) DEFAULT '1' COMMENT '参数是否生效',
  `param_type` varchar(64) NOT NULL DEFAULT '参数类型，默认为DEFAULT',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

-- 短信表
CREATE TABLE `sms` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1024) DEFAULT NULL,
  `send_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `sender_id` bigint(11) NOT NULL COMMENT '发送人',
  `receiver_id` bigint(11) NOT NULL COMMENT  '接收者',
  `status` varchar(32) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

DROP DATABASE IF EXISTS ice;
CREATE DATABASE ice CHARACTER SET 'utf8'
COLLATE 'utf8_general_ci';

USE ice;

DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `commodity_class`;
DROP TABLE IF EXISTS `commodity`;
DROP TABLE IF EXISTS `commodity_stock`;
DROP TABLE IF EXISTS `in_business`;
DROP TABLE IF EXISTS `out_business`;
DROP TABLE IF EXISTS `in_storage_list`;
DROP TABLE IF EXISTS `in_storage_info`;
DROP TABLE IF EXISTS `out_storage_info`;
DROP TABLE IF EXISTS `out_storage_list`;
DROP TABLE IF EXISTS `out_storage_in_storage`;

--  商户表
CREATE TABLE `i_business` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `b_name` varchar(32) NOT NULL COMMENT '商户名称',
  `b_pwd` varchar(32) NOT NULL COMMENT '商户密码',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `phone` varchar(16) DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`b_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 类目表
CREATE TABLE `i_cat` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `name`  varchar(32) NOT NULL COMMENT '类目名称',
  `biz_id` bigint(20) unsigned NOT NULL COMMENT '商户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_biz_id` (`name`,`biz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 商品表
CREATE TABLE `i_commodity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `name` varchar(45) NOT NULL COMMENT '商品名',
  `unit` varchar(12) NOT NULL COMMENT '单位',
  `desc` varchar(2000) NOT NULL COMMENT '描述',
  `price` double(10,2) NOT NULL COMMENT '单价',
  `total` mediumint(9) NOT NULL COMMENT '数量',
  `state` varchar(12) NOT NULL COMMENT '状态',
  `cat_id` bigint(20) unsigned NOT NULL COMMENT '类目id',
  `sales` varchar(12) NOT NULL COMMENT '销售数量',
  `biz_id` bigint(20) unsigned NOT NULL COMMENT '商户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_biz_id` (`name`,`biz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据表
CREATE TABLE `i_lob` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `blob_value` longblob COMMENT '数据',
  `type` varchar(8) NOT NULL COMMENT '类型（IMG，JSON）',
  `com_id` bigint(20) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 订单表
CREATE TABLE `i_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `num` mediumint(9) NOT NULL COMMENT '数量',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `state` varchar(16) NOT NULL COMMENT '订单状态',
  `address` varchar(255) NOT NULL COMMENT '送货地址',
  `phone` varchar(16) NOT NULL COMMENT '电话',
  `process` varchar(255) DEFAULT NULL COMMENT '进度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户表
CREATE TABLE `i_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `u_name` varchar(32) NOT NULL COMMENT '用户名',
  `u_pwd` varchar(32) NOT NULL COMMENT '密码',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


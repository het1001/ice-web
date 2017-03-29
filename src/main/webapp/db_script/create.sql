DROP DATABASE IF EXISTS ice;
CREATE DATABASE ice CHARACTER SET 'utf8'
COLLATE 'utf8_general_ci';

USE ice;

DROP TABLE IF EXISTS `i_business`;
DROP TABLE IF EXISTS `i_cat`;
DROP TABLE IF EXISTS `i_com`;
DROP TABLE IF EXISTS `i_com_in`;
DROP TABLE IF EXISTS `i_com_in_item`;
DROP TABLE IF EXISTS `i_lob`;
DROP TABLE IF EXISTS `i_order`;
DROP TABLE IF EXISTS `i_order_list`;
DROP TABLE IF EXISTS `i_order_trace`;
DROP TABLE IF EXISTS `i_user`;

--  商户表
CREATE TABLE `i_business` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `b_name` varchar(32) NOT NULL COMMENT '商户名称',
  `b_pwd` varchar(32) NOT NULL COMMENT '商户密码',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `phone` varchar(16) DEFAULT NULL COMMENT '电话',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`b_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 类目表
CREATE TABLE `i_cat` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name`  varchar(32) NOT NULL COMMENT '类目名称',
  `biz_id` bigint(20) unsigned NOT NULL COMMENT '商户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_biz_id` (`name`,`biz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 商品表
CREATE TABLE `i_com` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(45) NOT NULL COMMENT '商品码',
  `name` varchar(45) NOT NULL COMMENT '商品名',
  `brand` varchar(100) DEFAULT NULL COMMENT '品牌',
  `standard_pice` mediumint(11) NOT NULL COMMENT '规格/件 单位（支）',
  `price_pi` double(10, 2) NOT NULL COMMENT '上柜价/件',
  `price_br` double(10, 2) NOT NULL COMMENT '上柜价/支',
  `retail_price_br` double(10, 2) NOT NULL COMMENT '零售/支',
  `profit_pi` double(10, 2) DEFAULT NULL COMMENT '终端利润/件',
  `profit_br` double(10, 2) DEFAULT NULL COMMENT '终端利润/支',
  `person_type` varchar(100) DEFAULT NULL COMMENT '针对人群',
  `position` varchar(200) DEFAULT NULL COMMENT '陈列位置',
  `promotion` varchar(200) DEFAULT NULL COMMENT '促销',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述（口味介绍）',
  `total` mediumint(9) NOT NULL COMMENT '数量',
  `sales` mediumint(9) DEFAULT NULL COMMENT '销量',
  `state` varchar(12) DEFAULT NULL COMMENT '状态（ONLINE：1, OFFLINE：0 ）',
  `cat_id` bigint(20) unsigned NOT NULL COMMENT '类目id',
  `biz_id` bigint(20) unsigned NOT NULL COMMENT '商户id',
  `img_key` varchar(100) DEFAULT NULL COMMENT '图片key',
  `bar_code` varchar(13) DEFAULT NULL COMMENT '条形码',
  `bar_img_key` varchar(100) DEFAULT NULL COMMENT '条形码图片key',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_biz_id` (`name`,`biz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 商品进货表
CREATE TABLE `i_com_in` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `in_num` varchar(20) NOT NULL COMMENT '进货单号',
  `description` varchar(500) DEFAULT NULL COMMENT '描述信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 商品入库表
CREATE TABLE `i_com_in_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `in_id` bigint(20) unsigned NOT NULL COMMENT '进货单ID',
  `com_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `com_name` varchar(64) NOT NULL COMMENT '商品名称',
  `com_standard` mediumint(9) NOT NULL COMMENT '商品规格',
  `price_pi` double(10, 2) NOT NULL COMMENT '单价/件',
  `price_br` double(10, 2) NOT NULL COMMENT '单价/支',
  `num` int(11) NOT NULL COMMENT '数量',
  `total` double(10, 2) NOT NULL COMMENT '总价',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据表
CREATE TABLE `i_lob` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `blob_value` longblob COMMENT '数据',
  `type` varchar(8) NOT NULL COMMENT '类型（IMG，JSON）',
  `com_id` bigint(20) NOT NULL COMMENT '商品id',
  `name` VARCHAR (100) NOT NULL COMMENT 'key',
  `active` tinyint (4) NOT NULL COMMENT '是否生效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 订单表
CREATE TABLE `i_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_num` varchar(16) NOT NULL COMMENT '订单号',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `price_total` DOUBLE NOT NULL COMMENT '总价',
  `state` varchar(16) NOT NULL COMMENT '订单状态',
  `address` varchar(255) NOT NULL COMMENT '送货地址',
  `phone` varchar(16) NOT NULL COMMENT '电话',
  `process` varchar(255) DEFAULT NULL COMMENT '进度',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 订单详情
CREATE TABLE `i_order_list` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `com_id` bigint(20) NOT NULL COMMENT '商品id',
  `com_name` VARCHAR(32) NOT NULL COMMENT '商品名称',
  `com_price` double(10, 2) NOT NULL COMMENT '商品单价',
  `com_num` int(11) NOT NULL COMMENT '商品数量',
  `com_unit` varchar(8) NOT NULL COMMENT '商品单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 订单历史记录
CREATE TABLE `i_order_trace` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `operate` VARCHAR(16) NOT NULL COMMENT '操作',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户表
CREATE TABLE `i_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `u_name` varchar(32) NOT NULL COMMENT '用户名',
  `u_pwd` varchar(32) DEFAULT NULL COMMENT '密码',
  `type` varchar(8) NOT NULL COMMENT '类型（商户， 普通用户）',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `real_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `state` varchar(32) DEFAULT NULL COMMENT '状态',
  `shop_name` varchar(128) DEFAULT NULL COMMENT '门店名称',
  `shop_img_key` varchar(50) DEFAULT NULL COMMENT '门店图片key',
  `auth_code` varchar(6) DEFAULT NULL COMMENT '验证码',
  `auth_time` datetime DEFAULT NULL COMMENT '验证码发送时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 收货地址
CREATE TABLE `i_user_delivery_addr` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `full_name` VARCHAR(20) NOT NULL COMMENT '姓名',
  `address` VARCHAR(200) NOT NULL COMMENT '地址',
  `phone` VARCHAR(45) NOT NULL COMMENT '手机号',
  `status` TINYINT(4) NOT NULL COMMENT '状态(1：默认收货地址，0：非默认)',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `modify_time` DATETIME NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
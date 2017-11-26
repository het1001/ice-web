DROP DATABASE IF EXISTS ice;
CREATE DATABASE ice CHARACTER SET 'utf8'
COLLATE 'utf8_general_ci';

USE ice;

DROP TABLE IF EXISTS `i_allot_dis_sal`;
DROP TABLE IF EXISTS `i_allot_district`;
DROP TABLE IF EXISTS `i_allot_salesman`;
DROP TABLE IF EXISTS `i_app_main_img`;
DROP TABLE IF EXISTS `i_arithmetic`;
DROP TABLE IF EXISTS `i_business`;
DROP TABLE IF EXISTS `i_cat`;
DROP TABLE IF EXISTS `i_com`;
DROP TABLE IF EXISTS `i_com_in`;
DROP TABLE IF EXISTS `i_com_in_item`;
DROP TABLE IF EXISTS `i_com_pic`;
DROP TABLE IF EXISTS `i_com_promotion`;
DROP TABLE IF EXISTS `i_lob`;
DROP TABLE IF EXISTS `i_order`;
DROP TABLE IF EXISTS `i_order_list`;
DROP TABLE IF EXISTS `i_order_trace`;
DROP TABLE IF EXISTS `i_shopping_cart`;
DROP TABLE IF EXISTS `i_user`;
DROP TABLE IF EXISTS `i_user_action_trace`;
DROP TABLE IF EXISTS `i_user_auth_code`;
DROP TABLE IF EXISTS `i_user_delivery_addr`;
DROP TABLE IF EXISTS `i_user_info`;
DROP TABLE IF EXISTS `i_user_phone_info`;

-- 片区人员
CREATE TABLE `i_allot_dis_sal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dis_id` bigint(20) NOT NULL COMMENT '片区id',
  `dis_name` varchar(128) NOT NULL COMMENT '片区名称',
  `sal_id` bigint(20) NOT NULL COMMENT '配送or业务员',
  `sal_name` varchar(128) NOT NULL COMMENT '人员姓名',
  `sal_phone` varchar(20) NOT NULL COMMENT '人员手机号',
  `sal_type` varchar(100) NOT NULL COMMENT '人员类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 片区
CREATE TABLE `i_allot_district` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '片区名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `i_allot_salesman` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `unique_key` varchar(20) DEFAULT NULL COMMENT '唯一标识',
  `name` varchar(128) NOT NULL COMMENT '人员',
  `phone` varchar(20) NOT NULL COMMENT '手机',
  `type` varchar(100) NOT NULL COMMENT '类型：配送 or 业务',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `i_app_main_img` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `image_key` varchar(100) DEFAULT NULL COMMENT 'imageKey',
  `active` tinyint(4) DEFAULT NULL COMMENT '是否使用',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `i_arithmetic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型：计算型or非计算型',
  `function` varchar(5000) NOT NULL COMMENT '表达式',
  `func_keys` varchar(100) DEFAULT NULL COMMENT '表达式未知参数key',
  `in_params` varchar(500) DEFAULT NULL COMMENT '入参',
  `out_param` varchar(100) DEFAULT NULL COMMENT '出参',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
  `price_pi` double(10,2) NOT NULL COMMENT '上柜价/件',
  `price_br` double(10,2) NOT NULL COMMENT '上柜价/支',
  `retail_price_br` double(10,2) NOT NULL COMMENT '零售/支',
  `profit_pi` double(10,2) DEFAULT NULL COMMENT '终端利润/件',
  `profit_br` double(10,2) DEFAULT NULL COMMENT '终端利润/支',
  `person_type` varchar(100) DEFAULT NULL COMMENT '针对人群',
  `position` varchar(200) DEFAULT NULL COMMENT '陈列位置',
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

CREATE TABLE `i_com_in_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `in_id` bigint(20) unsigned NOT NULL COMMENT '进货单ID',
  `com_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `com_name` varchar(64) NOT NULL COMMENT '商品名称',
  `com_standard` mediumint(9) NOT NULL COMMENT '商品规格',
  `price_pi` double(10,2) NOT NULL COMMENT '单价/件',
  `price_br` double(10,2) NOT NULL COMMENT '单价/支',
  `num` int(11) NOT NULL COMMENT '数量',
  `total` double(10,2) NOT NULL COMMENT '总价',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 商品图片
CREATE TABLE `i_com_pic` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `com_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `pic_key` varchar(128) NOT NULL COMMENT '图片key',
  `is_main` tinyint(4) NOT NULL COMMENT '是否是主图（首图）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `i_com_promotion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `com_id` bigint(20) NOT NULL COMMENT '商品ID',
  `com_name` varchar(45) NOT NULL COMMENT '商品名称',
  `arith_id` bigint(20) NOT NULL COMMENT '算法ID',
  `description` varchar(2000) NOT NULL COMMENT '促销说明',
  `params` varchar(500) DEFAULT NULL COMMENT '参数',
  `state` varchar(20) NOT NULL COMMENT '状态（ON_LINE：可用，OFF_LINE：不可用）',
  `effective_date` datetime DEFAULT NULL COMMENT '生效时间',
  `deadline` datetime DEFAULT NULL COMMENT '截止时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据表
CREATE TABLE `i_lob` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `oss_key` varchar(128) NOT NULL COMMENT '附件key',
  `is_used` tinyint(4) NOT NULL COMMENT '是否使用，0未使用，1使用',
  `where_use` varchar(100) DEFAULT NULL COMMENT '在哪里用了',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 订单表
CREATE TABLE `i_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_num` varchar(20) NOT NULL COMMENT '订单号',
  `phone` varchar(16) NOT NULL COMMENT '手机号',
  `user_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `price_total` double NOT NULL COMMENT '总价',
  `state` varchar(16) NOT NULL COMMENT '订单状态',
  `address` varchar(255) NOT NULL COMMENT '送货地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 订单详情
CREATE TABLE `i_order_list` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_num` varchar(20) NOT NULL COMMENT '订单号',
  `com_id` bigint(20) NOT NULL COMMENT '商品id',
  `com_name` varchar(32) NOT NULL COMMENT '商品名称',
  `com_standard` mediumint(9) DEFAULT NULL COMMENT '规格',
  `com_price` double(10,2) NOT NULL COMMENT '商品单价',
  `com_num` int(11) NOT NULL COMMENT '商品数量',
  `state` varchar(16) NOT NULL COMMENT '订单状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 订单历史记录
CREATE TABLE `i_order_trace` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_num` varchar(20) NOT NULL COMMENT '订单号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `operate` varchar(16) NOT NULL COMMENT '操作',
  `operate_display` varchar(100) DEFAULT NULL COMMENT '操作名称',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `i_shopping_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `com_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `com_num` int(11) DEFAULT NULL COMMENT '数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户表
CREATE TABLE `i_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `u_name` varchar(32) NOT NULL COMMENT '用户名',
  `u_pwd` varchar(32) DEFAULT NULL COMMENT '密码',
  `type` varchar(8) NOT NULL COMMENT '类型（商户， 普通用户）',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `real_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `shop_name` varchar(128) DEFAULT NULL COMMENT '商铺名称',
  `shop_address` varchar(256) DEFAULT NULL COMMENT '商铺地址',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `state` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  `token` varchar(60) DEFAULT NULL,
  `property` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `i_user_action_trace` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `phone` varchar(18) NOT NULL COMMENT '手机号',
  `operate` varchar(32) DEFAULT NULL COMMENT '操作',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `old_info` varchar(5000) DEFAULT NULL COMMENT '旧数据',
  `new_info` varchar(5000) DEFAULT NULL COMMENT '新数据',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户验证码
CREATE TABLE `i_user_auth_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `phone` varchar(18) NOT NULL COMMENT '手机号',
  `code` varchar(6) DEFAULT NULL COMMENT '验证码',
  `used` tinyint(4) DEFAULT NULL COMMENT '用户是否使用过',
  `device_unique_id` varchar(32) DEFAULT NULL COMMENT '设备唯一标识',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `use_time` timestamp NULL DEFAULT NULL COMMENT '使用时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_code` (`phone`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 收货地址
CREATE TABLE `i_user_delivery_addr` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `phone` varchar(45) NOT NULL COMMENT '用户手机号',
  `full_name` varchar(20) NOT NULL COMMENT '姓名',
  `address` varchar(200) NOT NULL COMMENT '地址',
  `del_phone` varchar(45) NOT NULL COMMENT '收货手机号',
  `status` tinyint(4) NOT NULL COMMENT '状态(1：默认收货地址，0：非默认)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `i_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `phone` varchar(18) DEFAULT NULL COMMENT '手机号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `shop_name` varchar(128) DEFAULT NULL COMMENT '店铺名称',
  `shop_address` varchar(256) DEFAULT NULL COMMENT '店铺地址',
  `shop_img_key` varchar(64) DEFAULT NULL COMMENT '店铺图片key',
  `freezer_type` varchar(200) DEFAULT NULL COMMENT '冰柜种类',
  `ark_time` datetime DEFAULT NULL COMMENT '投柜时间',
  `freezer_model` varchar(100) DEFAULT NULL COMMENT '冰柜型号',
  `district_id` bigint(20) DEFAULT NULL COMMENT '所属片区id',
  `is_access` tinyint(4) DEFAULT NULL COMMENT '是否通过审核',
  `audit_memo` varchar(250) DEFAULT NULL COMMENT '审核意见',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户手机信息
CREATE TABLE `i_user_phone_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `phone` varchar(18) DEFAULT NULL,
  `manufacturer` varchar(32) DEFAULT NULL COMMENT '手机制造商',
  `model` varchar(132) DEFAULT NULL COMMENT '手机型号',
  `device_unique_id` varchar(32) DEFAULT NULL COMMENT '设备唯一识别码',
  `device_id` varchar(16) DEFAULT NULL COMMENT '设备ID',
  `device_name` varchar(32) DEFAULT NULL COMMENT '设备名称',
  `sys_name` varchar(32) DEFAULT NULL COMMENT '系统名称',
  `sys_version` varchar(32) DEFAULT NULL COMMENT '系统版本',
  `imei` varchar(128) DEFAULT NULL,
  `app_version` varchar(128) DEFAULT NULL COMMENT 'app版本',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

-- admin/123456
INSERT INTO `i_user` (`create_time`, `modify_time`, `u_name`, `u_pwd`, `type`, `phone`, `real_name`, `last_login_time`, `state`) VALUES(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'admin','e10adc3949ba59abbe56e057f20f883e','BIZ',NULL,NULL,CURRENT_TIMESTAMP,NULL);
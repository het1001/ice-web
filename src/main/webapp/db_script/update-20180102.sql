ALTER TABLE i_cat ADD COLUMN `orderr` tinyint(4) NOT NULL COMMENT '顺序' AFTER `name`;
ALTER TABLE i_cat ADD COLUMN `type` VARCHAR(32) NULL COMMENT '类型' AFTER `biz_id`;

ALTER TABLE i_user ADD COLUMN `freezer_type` varchar(200) NULL COMMENT '冰柜种类';
ALTER TABLE i_user ADD COLUMN `district_id` bigint(200) NULL COMMENT '所属片区ID';
ALTER TABLE i_user ADD COLUMN `standby_phone` varchar(18) NULL COMMENT '备用手机号';

ALTER TABLE i_user_info ADD COLUMN `standby_phone` varchar(18) NULL COMMENT '备用手机号';

ALTER TABLE i_order ADD COLUMN `exp_del_time_des` VARCHAR(300) NULL COMMENT 'expected_delivery_time_des预计送达时间描述' AFTER `address`;
ALTER TABLE i_order ADD COLUMN `time` VARCHAR(20) NULL COMMENT '订单完成用时' AFTER `exp_del_time_des`;

ALTER TABLE i_com ADD COLUMN `day_sales` INT(11) NULL COMMENT '昨日销量', ADD COLUMN `week_salse` INT(11) NULL COMMENT '一周内销量' AFTER `day_sales`;
ALTER TABLE i_com CHANGE `cat_id` `pric_cat_id` BIGINT(20) UNSIGNED NULL COMMENT '价格类目id', ADD COLUMN `pack_cat_id` BIGINT(20) UNSIGNED  NULL COMMENT '包装类型id' AFTER `pric_cat_id`;
ALTER TABLE i_com ADD COLUMN `pack_cat` VARCHAR(100) NULL COMMENT '包装类型名称' AFTER `pack_cat_id`;
ALTER TABLE i_com ADD COLUMN `brand_id` BIGINT NULL COMMENT '品牌id' AFTER `name`;
ALTER TABLE i_com ADD COLUMN `weight` INT NULL COMMENT '重量（g）' AFTER `bar_img_key`;


ALTER TABLE i_cat ADD COLUMN `orderr` tinyint(4) NOT NULL COMMENT '顺序';

ALTER TABLE i_user ADD COLUMN `freezer_type` varchar(200) NULL COMMENT '冰柜种类';
ALTER TABLE i_user ADD COLUMN `district_id` bigint(200) NULL COMMENT '所属片区ID';
ALTER TABLE i_user ADD COLUMN `standby_phone` varchar(18) NULL COMMENT '备用手机号';

ALTER TABLE i_user_info ADD COLUMN `standby_phone` varchar(18) NULL COMMENT '备用手机号';

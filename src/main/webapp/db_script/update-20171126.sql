CREATE INDEX phone_Index ON i_order(phone);
CREATE INDEX orderNum_Index ON i_order_list(order_num);

ALTER TABLE i_order_list ADD COLUMN `finish_flag` tinyint(4) NOT NULL COMMENT '0：未完成，1：完成';
ALTER TABLE i_order_list ADD COLUMN `create_time` datetime NOT NULL COMMENT '创建时间';
ALTER TABLE i_order_list ADD COLUMN `modify_time` datetime NOT NULL COMMENT '更新时间';

ALTER TABLE i_arithmetic ADD COLUMN `type` tinyint(4) DEFAULT NULL COMMENT '类型：计算型or非计算型';
insert into `i_arithmetic` (`name`, `type`, `function`, `func_keys`, `in_params`, `out_param`, `create_time`, `modify_time`) values('买x个商品送y个z商品','0','x','[{\"key\":\"x\",\"type\":\"inputNum\"},{\"key\":\"y\",\"type\":\"inputNum\"},{\"key\":\"z\",\"type\":\"commodity\"}]','total','total',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
package com.het.ice.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/6/11.
 */
public enum OrderOprateEnum {

    CREATE("CREATE", "创建订单"),

    ACCEPT("ACCEPT", "接收订单"),

    COMPLETE("COMPLETE", "确认收货"),

    CANCEL("CANCEL", "取消订单");

    OrderOprateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;

    public static OrderOprateEnum getByCode(String code) {

        OrderOprateEnum[] enums = values();

        for (OrderOprateEnum orderStateEnum : enums) {
            if (StringUtils.equals(code, orderStateEnum.getCode())) {
                return orderStateEnum;
            }
        }

        return null;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }
}

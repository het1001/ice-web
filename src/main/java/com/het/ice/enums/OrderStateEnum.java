package com.het.ice.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/6/11.
 */
public enum OrderStateEnum {

    CREATED("CREATED", "创建"),

    ACCEPT("ACCEPT", "接受"),

    COMPLETED("COMPLETED", "完成"),

    CANCELED("CANCELED", "取消");

    OrderStateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;

    public static OrderStateEnum getByCode(String code) {

        OrderStateEnum[] enums = values();

        for (OrderStateEnum orderStateEnum : enums) {
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

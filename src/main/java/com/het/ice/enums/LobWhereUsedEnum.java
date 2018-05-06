package com.het.ice.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/6/11.
 */
public enum LobWhereUsedEnum {

    COMMODITY_PIC("COMMODITY_PIC", "商品图"),

    USER_SHOP_PIC("USER_SHOP_PIC", "用户店铺图"),

    APP_MAIN_PIC("APP_MAIN_PIC", "app首页图");

    LobWhereUsedEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;

    public static LobWhereUsedEnum getByCode(String code) {

        LobWhereUsedEnum[] enums = values();

        for (LobWhereUsedEnum lobWhereUsedEnum : enums) {
            if (StringUtils.equals(code, lobWhereUsedEnum.getCode())) {
                return lobWhereUsedEnum;
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

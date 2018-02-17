package com.het.ice.enums;

import org.apache.commons.lang3.StringUtils;

public enum CatTypeEnum {

    PRICE("PRICE", "价格分类"),

    PACKAGE("PACKAGE", "包装类型");

    CatTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
    }

    private String code;

    private String desc;

    public static CatTypeEnum getByCode(String code) {
        CatTypeEnum[] enums = values();
        for (CatTypeEnum catTypeEnum : enums) {
            if (StringUtils.equals(code, catTypeEnum.getCode())) {
                return catTypeEnum;
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

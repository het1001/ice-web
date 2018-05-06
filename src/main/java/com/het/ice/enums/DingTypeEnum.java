package com.het.ice.enums;

import org.apache.commons.lang3.StringUtils;

public enum DingTypeEnum {

    TEXT("text", "文本"),

    MARKDOWN("markdown", "文本");

    DingTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
    }

    private String code;

    private String desc;

    public static DingTypeEnum getByCode(String code) {
        DingTypeEnum[] enums = values();
        for (DingTypeEnum dingTypeEnum : enums) {
            if (StringUtils.equals(code, dingTypeEnum.getCode())) {
                return dingTypeEnum;
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

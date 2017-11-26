package com.het.ice.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/5/29.
 */
public enum UserOperateEnum {

    CREATE("CREATE", "创建"),

    GET_AUTH_CODE("GET_AUTH_CODE", "获取验证码"),

    AUTH("AUTH", "验证"),

    SET_PASSWORD("SET_PASSWORD", "设置密码"),

    RE_SET_PASSWORD("RE_SET_PASSWORD", "重置密码"),

    COMPLETE_INFO("COMPLETE_INFO", "完善信息"),

    LOGIN("LOGIN", "登录"),

    AUTO_LOGIN("AUTO_LOGIN", "自动登录"),

    UPDATE_APP_VERSION("UPDATE_APP_VERSION", "更新app版本");

    UserOperateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;

    /**
     *
     * @param code
     * @return
     */
    public static UserOperateEnum getByCode(String code) {

        UserOperateEnum[] enums = values();

        for (UserOperateEnum userOperateEnum : enums) {
            if (StringUtils.equals(code, userOperateEnum.getCode())) {
                return userOperateEnum;
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
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}

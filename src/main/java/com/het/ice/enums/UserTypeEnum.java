package com.het.ice.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 用户类型枚举
 * 
 * @author Administrator
 *
 */
public enum UserTypeEnum {

	BIZ("BIZ", "商户"),

	NORMAL("NORMAL", "普通用户");

	UserTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String code;

	private String desc;

	public static UserTypeEnum getByCode(String code) {

		UserTypeEnum[] enums = values();

		for (UserTypeEnum lobTypeEnum : enums) {
			if (StringUtils.equals(code, lobTypeEnum.getCode())) {
				return lobTypeEnum;
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

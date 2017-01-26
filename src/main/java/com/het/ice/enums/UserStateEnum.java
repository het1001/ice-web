package com.het.ice.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 用户状态枚举
 * 
 * @author Administrator
 *
 */
public enum UserStateEnum {

	CREATE("CREATE", "创建"),

	AUTHED("AUTHED", "验证通过"),

	NORMAL("NORMAL", "正常"),

	FREEAE("FREEAE", "冻结"),

	AUDITING("AUDITING", "审核中"),

	AUDIT_NO("AUDIT_NO", "审核不通过");

	UserStateEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String code;

	private String desc;

	public static UserStateEnum getByCode(String code) {

		UserStateEnum[] enums = values();

		for (UserStateEnum lobTypeEnum : enums) {
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

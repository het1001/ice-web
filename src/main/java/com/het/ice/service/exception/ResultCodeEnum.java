package com.het.ice.service.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 业务结果码枚举
 * 
 * @author Administrator
 *
 */
public enum ResultCodeEnum {

	USERNAME_IS_BLANK("USERNAME_IS_BLANK", "用户名为空"),

	USERNAME_NOT_EXIST("USERNAME_NOT_EXIST", "用户名不存在"),

	PWD_IS_BLANK("PWD_IS_BLANK", "密码为空"),

	PWD_CHECK_FAILED("PWD_CHECK_FAILED", "密码校验失败"),

	NORMAL("NORMAL", "普通用户");

	ResultCodeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String code;

	private String desc;

	public static ResultCodeEnum getByCode(String code) {

		ResultCodeEnum[] enums = values();

		for (ResultCodeEnum lobTypeEnum : enums) {
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

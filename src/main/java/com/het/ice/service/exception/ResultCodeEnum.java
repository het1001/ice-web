package com.het.ice.service.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 业务结果码枚举
 * 
 * @author Administrator
 *
 */
public enum ResultCodeEnum {

	USER_EXCEPTION("USER_EXCEPTION", "用户状态异常"),

	USER_TYPE_ERROR("USER_TYPE_ERROR", "用户状态错误"),

	USER_IS_FREEAED("USER_IS_FREEAED", "用户已冻结"),

	USER_IS_NOT_FREEAED("USER_IS_NOT_FREEAED", "用户未冻结"),

	USERNAME_IS_BLANK("USERNAME_IS_BLANK", "用户名为空"),

	USERNAME_NOT_EXIST("USERNAME_NOT_EXIST", "用户名不存在"),

	PWD_IS_BLANK("PWD_IS_BLANK", "密码为空"),

	PWD_CHECK_FAILED("PWD_CHECK_FAILED", "密码校验失败"),

	USER_AUTHED("USER_AUTHED", "用户已验证通过"),

	USER_NOT_AUTHED("USER_NOT_AUTHED", "用户未验证通过"),

	USER_NOT_EXIST("USER_NOT_EXIST", "用户不存在"),

	USER_NOT_COMPLEAT_REGISTER("USER_NOT_COMPLEAT_REGISTER", "用户还未完成注册"),

	USER_NOT_SET_PASSWORD("USER_NOT_SET_PASSWORD", "用户还未设置密码"),

	USER_FREEAED("USER_FREEAED", "用户被冻结"),

	AUTH_FAILED("AUTH_FAILED", "验证码校验失败"),

	AUTH_CODE_SEND_FAILED("AUTH_CODE_SEND_FAILED", "验证码发送失败"),

	AUTH_CODE_SEND_TIMES_OUT("AUTH_CODE_SEND_TIMES_OUT", "验证码发送次数已超出限制"),

	AUTH_CODE_SEND_TOO_FAST("AUTH_CODE_SEND_TOO_FAST", "验证码发送太频繁（短于1分钟）");

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

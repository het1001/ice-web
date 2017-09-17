package com.het.ice.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 促销状态枚举
 * 
 * @author Administrator
 *
 */
public enum PromotionStateEnum {

	ON_LINE("ON_LINE", "可用"),

	OFF_LINE("OFF_LINE", "不可用");

	PromotionStateEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String code;

	private String desc;

	public static PromotionStateEnum getByCode(String code) {

		PromotionStateEnum[] enums = values();

		for (PromotionStateEnum promotionStateEnum : enums) {
			if (StringUtils.equals(code, promotionStateEnum.getCode())) {
				return promotionStateEnum;
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

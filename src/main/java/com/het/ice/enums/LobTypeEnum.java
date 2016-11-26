package com.het.ice.enums;

import org.apache.commons.lang3.StringUtils;

public enum LobTypeEnum {

	MAIN("MAIN", "商品显示图"),

	THUMBNAIL("THUMB", "缩略图"),

	NORMAL("NORMAL", "普通图");

	LobTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String code;

	private String desc;

	public static LobTypeEnum getByCode(String code) {

		LobTypeEnum[] enums = values();

		for (LobTypeEnum lobTypeEnum : enums) {
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
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

}

package com.het.ice.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 业务员类型枚举
 * 
 * @author Administrator
 *
 */
public enum SalesmanTypeEnum {

	DELIVERYMEN("DELIVERYMEN", "配送员"),

	SALESMAN("SALESMAN", "业务员/推销员");

	SalesmanTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String code;

	private String desc;

	public static SalesmanTypeEnum getByCode(String code) {
		SalesmanTypeEnum[] enums = values();

		for (SalesmanTypeEnum salesmanTypeEnum : enums) {
			if (StringUtils.equals(code, salesmanTypeEnum.getCode())) {
				return salesmanTypeEnum;
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

package com.het.ice.model;

import com.het.ice.enums.CatTypeEnum;

public class Cat extends BaseModel {

	private static final long serialVersionUID = 6738727460408641424L;
	/**
	 * uid
	 */
	private String name;

	private int orderr;

	private long bizId;

	private CatTypeEnum type;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the bizId
	 */
	public long getBizId() {
		return bizId;
	}

	/**
	 * @param bizId
	 *            the bizId to set
	 */
	public void setBizId(long bizId) {
		this.bizId = bizId;
	}

	public int getOrderr() {
		return orderr;
	}

	public void setOrderr(int orderr) {
		this.orderr = orderr;
	}

	public CatTypeEnum getType() {
		return type;
	}

	public void setType(CatTypeEnum type) {
		this.type = type;
	}
}

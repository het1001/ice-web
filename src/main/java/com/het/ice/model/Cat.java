package com.het.ice.model;

public class Cat extends BaseModel {

	/**
	 * uid
	 */
	private static final long serialVersionUID = 2631548087992626585L;

	private String name;

	private long bizId;

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

}

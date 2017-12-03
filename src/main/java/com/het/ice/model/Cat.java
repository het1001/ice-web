package com.het.ice.model;

public class Cat extends BaseModel {

	private static final long serialVersionUID = 6738727460408641424L;
	/**
	 * uid
	 */
	private String name;

	private int orderr;

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

	public int getOrderr() {
		return orderr;
	}

	public void setOrderr(int orderr) {
		this.orderr = orderr;
	}
}

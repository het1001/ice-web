package com.het.ice.dao.model;

public class CatDO extends BaseModel {

	/**
	 * uid
	 */
	private static final long serialVersionUID = 2631548087992626585L;

	private String name;

	private int orderr;

	private long bizId;

	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

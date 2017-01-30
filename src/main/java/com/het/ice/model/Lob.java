package com.het.ice.model;

import com.het.ice.enums.LobTypeEnum;

public class Lob extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 19077003097334588L;

	private String name;

	private byte[] blobValue;

	private LobTypeEnum type;

	private int active;

	private long comId;

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
	 * @return the blobValue
	 */
	public byte[] getBlobValue() {
		return blobValue;
	}

	/**
	 * @param blobValue
	 *            the blobValue to set
	 */
	public void setBlobValue(byte[] blobValue) {
		this.blobValue = blobValue;
	}

	/**
	 * @return the type
	 */
	public LobTypeEnum getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(LobTypeEnum type) {
		this.type = type;
	}

	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}

	/**
	 * @return the comId
	 */
	public long getComId() {
		return comId;
	}

	/**
	 * @param comId
	 *            the comId to set
	 */
	public void setComId(long comId) {
		this.comId = comId;
	}

}

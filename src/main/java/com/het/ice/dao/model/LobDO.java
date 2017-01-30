package com.het.ice.dao.model;

/**
 * 
 * @author Administrator
 *
 */
public class LobDO extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 19077003097334588L;

	private byte[] blobValue;

	private String name;

	private int active;

	private String type;

	private long comId;

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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
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

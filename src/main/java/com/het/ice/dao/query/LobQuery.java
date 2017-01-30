package com.het.ice.dao.query;

import java.io.Serializable;

/**
 * 
 * 
 * @author Administrator
 *
 */
public class LobQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2674715853350535456L;

	private long comId;

	private String type;

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

}

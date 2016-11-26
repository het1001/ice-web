package com.het.ice.dao.query;

import java.io.Serializable;

public class CommodityInItemQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1825267241918657187L;

	private long comId;

	private int start;

	private int limit;

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
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

}

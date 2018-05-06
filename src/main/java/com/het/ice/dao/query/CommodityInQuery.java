package com.het.ice.dao.query;

import java.io.Serializable;
import java.util.Date;

public class CommodityInQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1825267241918657187L;

	private Date fromTime;

	private Date endTime;

	private int start;

	private int limit;

	/**
	 * @return the fromTime
	 */
	public Date getFromTime() {
		return fromTime;
	}

	/**
	 * @param fromTime
	 *            the fromTime to set
	 */
	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

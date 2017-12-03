package com.het.ice.dao.query;

import java.io.Serializable;

public class UserQuery implements Serializable {


	private static final long serialVersionUID = -7388970963266775301L;

	private String phone;

	private String state;

	private String freezerType;

	private long districtId;

	private int start;

	private int limit;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFreezerType() {
		return freezerType;
	}

	public void setFreezerType(String freezerType) {
		this.freezerType = freezerType;
	}

	public long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}
}

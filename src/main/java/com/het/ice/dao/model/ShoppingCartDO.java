package com.het.ice.dao.model;

public class ShoppingCartDO extends BaseModel {

	/**
	 * uid
	 */
	private static final long serialVersionUID = 2278193782902225058L;

	private String phone;

	private long comId;

	private int comNum;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getComId() {
		return comId;
	}

	public void setComId(long comId) {
		this.comId = comId;
	}

	public int getComNum() {
		return comNum;
	}

	public void setComNum(int comNum) {
		this.comNum = comNum;
	}
}

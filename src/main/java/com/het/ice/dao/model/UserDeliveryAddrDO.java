package com.het.ice.dao.model;

/**
 * 用户收货地址
 */
public class UserDeliveryAddrDO extends BaseModel {

	private static final long serialVersionUID = -7465268949841121402L;

	private long userId;

	private String fullName;

	private String phone;

	private String address;

	private int status;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

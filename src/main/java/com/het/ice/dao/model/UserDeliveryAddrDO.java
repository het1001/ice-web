package com.het.ice.dao.model;

/**
 * 用户收货地址
 */
public class UserDeliveryAddrDO extends BaseModel {

	private static final long serialVersionUID = -7465268949841121402L;

	private String phone;

	private String fullName;

	private String address;

	private String delPhone;

	private int status;

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

	public String getDelPhone() {
		return delPhone;
	}

	public void setDelPhone(String delPhone) {
		this.delPhone = delPhone;
	}
}

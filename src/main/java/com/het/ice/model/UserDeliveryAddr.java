package com.het.ice.model;

/**
 *
 */
public class UserDeliveryAddr extends BaseModel {

	private static final long serialVersionUID = -2718676029649834894L;

	/** 手机号 */
	private String phone;

	/** 姓名 */
	private String fullName;

	/** 地址 */
	private String address;

	/** 收货手机号 */
	private String delPhone;

	/** 状态 */
	private int status;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDelPhone() {
		return delPhone;
	}

	public void setDelPhone(String delPhone) {
		this.delPhone = delPhone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

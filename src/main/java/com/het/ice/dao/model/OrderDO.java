package com.het.ice.dao.model;

/**
 * 订单模型
 * 
 * @author Administrator
 *
 */
public class OrderDO extends BaseModel {

	private static final long serialVersionUID = -6756898505256760393L;

	/** 订单号 */
	private String orderNum;

	/** 手机号 */
	private String phone;

	/** 用户名 */
	private String userName;

	/** 总价 */
	private double priceTotal;

	/** 状态 */
	private String state;

	/** 地址 */
	private String address;

	private String expDelTimeDes;

	private String time;

	/**
	 * @return the orderNum
	 */
	public String getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum
	 *            the orderNum to set
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return the priceTotal
	 */
	public double getPriceTotal() {
		return priceTotal;
	}

	/**
	 * @param priceTotal
	 *            the priceTotal to set
	 */
	public void setPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getExpDelTimeDes() {
		return expDelTimeDes;
	}

	public void setExpDelTimeDes(String expDelTimeDes) {
		this.expDelTimeDes = expDelTimeDes;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}

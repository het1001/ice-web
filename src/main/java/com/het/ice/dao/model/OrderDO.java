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

	/** 用户id */
	private long userId;

	/** 总价 */
	private double priceTotal;

	/** 状态 */
	private String state;

	/** 地址 */
	private String address;

	/** 电话 */
	private String phone;

	/** 流转记录 */
	private String process;

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
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
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

	/**
	 * @return the process
	 */
	public String getProcess() {
		return process;
	}

	/**
	 * @param process
	 *            the process to set
	 */
	public void setProcess(String process) {
		this.process = process;
	}

}

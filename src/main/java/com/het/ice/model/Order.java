package com.het.ice.model;

import java.util.List;

/**
 * 订单model
 * 
 * @author Administrator
 *
 */
public class Order extends BaseModel {

	private static final long serialVersionUID = 4365818390130475826L;
	/** 订单号 */
	private String orderNum;

	/** 电话 */
	private String phone;

	/** 用户名 */
	private String userName;

	/** 总价 */
	private double priceTotal;

	/** 状态 */
	private String state;

	/** 地址 */
	private String address;

	private List<OrderList> orderLists;

	private List<OrderTrace> orderTraces;

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

	public List<OrderList> getOrderLists() {
		return orderLists;
	}

	public void setOrderLists(List<OrderList> orderLists) {
		this.orderLists = orderLists;
	}

	public List<OrderTrace> getOrderTraces() {
		return orderTraces;
	}

	public void setOrderTraces(List<OrderTrace> orderTraces) {
		this.orderTraces = orderTraces;
	}
}

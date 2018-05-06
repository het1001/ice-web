package com.het.ice.model;

public class OrderTrace extends BaseModel {

	private static final long serialVersionUID = 1671768718529327203L;

	private String orderNum;

	private String operate;

	private String description;

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

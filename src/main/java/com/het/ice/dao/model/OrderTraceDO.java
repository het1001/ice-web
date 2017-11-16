package com.het.ice.dao.model;

public class OrderTraceDO extends BaseModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 8792749986024654560L;

	private String orderNum;

	private String operate;

	private String operateDisplay;

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

	public String getOperateDisplay() {
		return operateDisplay;
	}

	public void setOperateDisplay(String operateDisplay) {
		this.operateDisplay = operateDisplay;
	}
}

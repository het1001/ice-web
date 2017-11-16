package com.het.ice.dao.model;

/**
 *
 */
public class OrderListDO extends BaseModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 7198862148499550828L;

	private String orderNum;

	private long comId;

	private String comName;

	private int comStandard;

	private double comPrice;

	private int comNum;

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public long getComId() {
		return comId;
	}

	public void setComId(long comId) {
		this.comId = comId;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public int getComStandard() {
		return comStandard;
	}

	public void setComStandard(int comStandard) {
		this.comStandard = comStandard;
	}

	public double getComPrice() {
		return comPrice;
	}

	public void setComPrice(double comPrice) {
		this.comPrice = comPrice;
	}

	public int getComNum() {
		return comNum;
	}

	public void setComNum(int comNum) {
		this.comNum = comNum;
	}
}

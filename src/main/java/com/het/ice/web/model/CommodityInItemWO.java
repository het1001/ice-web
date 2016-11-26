package com.het.ice.web.model;

import java.io.Serializable;

public class CommodityInItemWO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3950132524774341835L;

	/** 商品id */
	private String comId;

	/** 单价（件） */
	private double pricePi;

	/** 数量 */
	private int num;

	/**
	 * @return the comId
	 */
	public String getComId() {
		return comId;
	}

	/**
	 * @param comId
	 *            the comId to set
	 */
	public void setComId(String comId) {
		this.comId = comId;
	}

	/**
	 * @return the pricePi
	 */
	public double getPricePi() {
		return pricePi;
	}

	/**
	 * @param pricePi
	 *            the pricePi to set
	 */
	public void setPricePi(double pricePi) {
		this.pricePi = pricePi;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num
	 *            the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "{comId=" + this.comId + ",num=" + this.num + ",pricePi=" + this.pricePi + "}";
	}
}

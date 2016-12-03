package com.het.ice.web.model;

import java.io.Serializable;

/**
 * 商品前端模型
 */
public class CommodityWO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3950132524774341835L;

	/** ID */
	private String id;

	/** 名称 */
	private String name;

	/** 规格/件 */
	private int standardPice;

	/** 上柜价/件 */
	private double pricePi;

	/** 上柜价/支 */
	private double priceBr;

	/** 零售/支 */
	private double retailPriceBr;

	/** 描述（口味介绍） */
	private String desc;

	/** 针对人群 */
	private String personType;

	/** 陈列位置 */
	private String position;

	/** 促销 */
	private String promotion;

	/** 品牌 */
	private String brand;

	/** 商品key */
	private String fileKey;

	/** 条形码 */
	private String barCode;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the standardPice
	 */
	public int getStandardPice() {
		return standardPice;
	}

	/**
	 * @param standardPice
	 *            the standardPice to set
	 */
	public void setStandardPice(int standardPice) {
		this.standardPice = standardPice;
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
	 * @return the priceBr
	 */
	public double getPriceBr() {
		return priceBr;
	}

	/**
	 * @param priceBr
	 *            the priceBr to set
	 */
	public void setPriceBr(double priceBr) {
		this.priceBr = priceBr;
	}

	/**
	 * @return the retailPriceBr
	 */
	public double getRetailPriceBr() {
		return retailPriceBr;
	}

	/**
	 * @param retailPriceBr
	 *            the retailPriceBr to set
	 */
	public void setRetailPriceBr(double retailPriceBr) {
		this.retailPriceBr = retailPriceBr;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the personType
	 */
	public String getPersonType() {
		return personType;
	}

	/**
	 * @param personType
	 *            the personType to set
	 */
	public void setPersonType(String personType) {
		this.personType = personType;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the promotion
	 */
	public String getPromotion() {
		return promotion;
	}

	/**
	 * @param promotion
	 *            the promotion to set
	 */
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand
	 *            the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the fileKey
	 */
	public String getFileKey() {
		return fileKey;
	}

	/**
	 * @param fileKey
	 *            the fileKey to set
	 */
	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	/**
	 *
	 * @return
     */
	public String getBarCode() {
		return barCode;
	}

	/**
	 *
	 * @param barCode
     */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
}

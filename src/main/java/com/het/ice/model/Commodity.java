package com.het.ice.model;

import java.util.List;

public class Commodity extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -758469458288705707L;

	/** 商品码 */
	private String code;

	/** 名称 */
	private String name;

	/** 规格/件（单位：支） */
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

	/** 终端利润/件 */
	private double profitPi;

	/** 终端利润/支 */
	private double profitBr;

	/** 品牌 */
	private String brand;

	/** 总量（库存） */
	private int total;

	/** 销量 */
	private int sales;

	/** 状态 */
	private int state;

	/** 类目id */
	private long catId;

	/** 商户id */
	private long bizId;

	/** 商品主图key */
	private String imgKey;

	/** 商品次图keys */
	private List<String> imgKeys;

	/** 条形码 */
	private String barCode;

	/** 条形码图形key */
	private String barImgKey;

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
	 * @return the profitPi
	 */
	public double getProfitPi() {
		return profitPi;
	}

	/**
	 * @param profitPi
	 *            the profitPi to set
	 */
	public void setProfitPi(double profitPi) {
		this.profitPi = profitPi;
	}

	/**
	 * @return the profitBr
	 */
	public double getProfitBr() {
		return profitBr;
	}

	/**
	 * @param profitBr
	 *            the profitBr to set
	 */
	public void setProfitBr(double profitBr) {
		this.profitBr = profitBr;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the sales
	 */
	public int getSales() {
		return sales;
	}

	/**
	 * @param sales
	 *            the sales to set
	 */
	public void setSales(int sales) {
		this.sales = sales;
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
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return the catId
	 */
	public long getCatId() {
		return catId;
	}

	/**
	 * @param catId
	 *            the catId to set
	 */
	public void setCatId(long catId) {
		this.catId = catId;
	}

	/**
	 * @return the bizId
	 */
	public long getBizId() {
		return bizId;
	}

	/**
	 * @param bizId
	 *            the bizId to set
	 */
	public void setBizId(long bizId) {
		this.bizId = bizId;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the imgKey
	 */
	public String getImgKey() {
		return imgKey;
	}

	/**
	 * @param imgKey
	 *            the imgKey to set
	 */
	public void setImgKey(String imgKey) {
		this.imgKey = imgKey;
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

	/**
	 *
	 * @return
     */
	public String getBarImgKey() {
		return barImgKey;
	}

	/**
	 *
	 * @param barImgKey
     */
	public void setBarImgKey(String barImgKey) {
		this.barImgKey = barImgKey;
	}

	public List<String> getImgKeys() {
		return imgKeys;
	}

	public void setImgKeys(List<String> imgKeys) {
		this.imgKeys = imgKeys;
	}
}

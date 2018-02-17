package com.het.ice.dao.query;

import java.io.Serializable;

public class CommodityQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7444091985488728292L;

	private long bizId;

	private long brandId;

	private long pricCatId;

	private long packCatId;

	private String[] brandIds;

	private String[] pricCatIds;

	private String[] packCatIds;

	private String name;

	/** 可选值：1、空 */
	private String status;

	private int start;

	private int limit;

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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	public long getPricCatId() {
		return pricCatId;
	}

	public void setPricCatId(long pricCatId) {
		this.pricCatId = pricCatId;
	}

	public long getPackCatId() {
		return packCatId;
	}

	public void setPackCatId(long packCatId) {
		this.packCatId = packCatId;
	}

	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public String[] getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(String[] brandIds) {
		this.brandIds = brandIds;
	}

	public String[] getPricCatIds() {
		return pricCatIds;
	}

	public void setPricCatIds(String[] pricCatIds) {
		this.pricCatIds = pricCatIds;
	}

	public String[] getPackCatIds() {
		return packCatIds;
	}

	public void setPackCatIds(String[] packCatIds) {
		this.packCatIds = packCatIds;
	}
}

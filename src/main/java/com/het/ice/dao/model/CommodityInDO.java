package com.het.ice.dao.model;

/**
 * 进货单DO
 * 
 * @author Administrator
 *
 */
public class CommodityInDO extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8442998179697528528L;

	private String inNum;

	private String description;

	/**
	 * @return the inNum
	 */
	public String getInNum() {
		return inNum;
	}

	/**
	 * @param inNum
	 *            the inNum to set
	 */
	public void setInNum(String inNum) {
		this.inNum = inNum;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}

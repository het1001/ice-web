package com.het.ice.model;

/**
 * 进货单列表DO
 * 
 * @author Administrator
 *
 */
public class CommodityInItem extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6255819412594351399L;

	private long inId;

	private long comId;

	private String comName;

	private int comStandard;

	private double pricePi;

	private double priceBr;

	private int num;

	private double total;

	/**
	 * @return the inId
	 */
	public long getInId() {
		return inId;
	}

	/**
	 * @param inId
	 *            the inId to set
	 */
	public void setInId(long inId) {
		this.inId = inId;
	}

	/**
	 * @return the comId
	 */
	public long getComId() {
		return comId;
	}

	/**
	 * @param comId
	 *            the comId to set
	 */
	public void setComId(long comId) {
		this.comId = comId;
	}

	/**
	 * @return the comName
	 */
	public String getComName() {
		return comName;
	}

	/**
	 * @param comName
	 *            the comName to set
	 */
	public void setComName(String comName) {
		this.comName = comName;
	}

	/**
	 * @return the comStandard
	 */
	public int getComStandard() {
		return comStandard;
	}

	/**
	 * @param comStandard
	 *            the comStandard to set
	 */
	public void setComStandard(int comStandard) {
		this.comStandard = comStandard;
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

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

}

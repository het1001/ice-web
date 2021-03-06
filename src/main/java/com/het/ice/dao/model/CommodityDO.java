package com.het.ice.dao.model;

public class CommodityDO extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -758469458288705707L;

	private String code;

	private String name;

	private long brandId;

	private String brand;

	private int standardPice;

	private double pricePi;

	private double priceBr;

	private double retailPriceBr;

	private double profitPi;

	private double profitBr;

	private String description;

	private String personType;

	private String position;

	private int total;

	private int sales;

	private int daySales;

	private int weekSales;

	private int state;

	private long pricCatId;

	private long packCatId;

	private String packCat;

	private long bizId;

	private String imgKey;

	private String barCode;

	private String barImgKey;

	private int weight;

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

	public String getImgKey() {
		return imgKey;
	}

	public void setImgKey(String imgKey) {
		this.imgKey = imgKey;
	}

	public int getDaySales() {
		return daySales;
	}

	public void setDaySales(int daySales) {
		this.daySales = daySales;
	}

	public int getWeekSales() {
		return weekSales;
	}

	public void setWeekSales(int weekSales) {
		this.weekSales = weekSales;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
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

	public String getPackCat() {
		return packCat;
	}

	public void setPackCat(String packCat) {
		this.packCat = packCat;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}

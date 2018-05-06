package com.het.ice.model;

public class CommodityPic extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -758469458288705707L;

	/** 商品ID */
	private long comId;

	/** 图片key */
	private String picKey;

	/** 是否是主图 */
	private int isMain;


	public long getComId() {
		return comId;
	}

	public void setComId(long comId) {
		this.comId = comId;
	}

	public String getPicKey() {
		return picKey;
	}

	public void setPicKey(String picKey) {
		this.picKey = picKey;
	}

	public int getIsMain() {
		return isMain;
	}

	public void setIsMain(int isMain) {
		this.isMain = isMain;
	}
}

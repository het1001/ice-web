package com.het.ice.dao.model;

/**
 * 
 * @author Administrator
 *
 */
public class CommodityPicDO extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 19077003097334588L;

	private long comId;

	private String picKey;

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

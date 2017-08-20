package com.het.ice.model;

public class Lob extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 19077003097334588L;

	private String ossKey;

	private int isUsed;

	private String whereUse;

	public String getOssKey() {
		return ossKey;
	}

	public void setOssKey(String ossKey) {
		this.ossKey = ossKey;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	public String getWhereUse() {
		return whereUse;
	}

	public void setWhereUse(String whereUse) {
		this.whereUse = whereUse;
	}
}

package com.het.ice.dao.model;

public class AllotDisSalDO extends BaseModel {

	private static final long serialVersionUID = 6604674908012094516L;

	private long disId;

	private String disName;

	private long salId;

	private String salName;

	private String salPhone;

	private String salType;

	public long getDisId() {
		return disId;
	}

	public void setDisId(long disId) {
		this.disId = disId;
	}

	public String getDisName() {
		return disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}

	public long getSalId() {
		return salId;
	}

	public void setSalId(long salId) {
		this.salId = salId;
	}

	public String getSalName() {
		return salName;
	}

	public void setSalName(String salName) {
		this.salName = salName;
	}

	public String getSalPhone() {
		return salPhone;
	}

	public void setSalPhone(String salPhone) {
		this.salPhone = salPhone;
	}

	public String getSalType() {
		return salType;
	}

	public void setSalType(String salType) {
		this.salType = salType;
	}
}

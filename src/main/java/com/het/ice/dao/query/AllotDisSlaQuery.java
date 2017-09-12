package com.het.ice.dao.query;

import java.io.Serializable;

public class AllotDisSlaQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7444091985488728292L;

	private long disId;

	private long salId;

	private String salType;

	public long getDisId() {
		return disId;
	}

	public void setDisId(long disId) {
		this.disId = disId;
	}

	public long getSalId() {
		return salId;
	}

	public void setSalId(long salId) {
		this.salId = salId;
	}

	public String getSalType() {
		return salType;
	}

	public void setSalType(String salType) {
		this.salType = salType;
	}
}

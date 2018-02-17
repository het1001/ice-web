package com.het.ice.model;

public class Brand extends BaseModel {

	private static final long serialVersionUID = -1440284459922805927L;
	/**
	 * uid
	 */
	private String name;

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
}

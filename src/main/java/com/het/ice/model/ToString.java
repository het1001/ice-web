package com.het.ice.model;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class ToString implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5135109265165393052L;

	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}

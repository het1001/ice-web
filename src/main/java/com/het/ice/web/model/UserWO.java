package com.het.ice.web.model;

import com.het.ice.model.ToString;

public class UserWO extends ToString {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5592404660601602034L;

	private String userName;

	private String passWord;

	private String newPassWord;

	private String type;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @param passWord
	 *            the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * @return the newPassWord
	 */
	public String getNewPassWord() {
		return newPassWord;
	}

	/**
	 * @param newPassWord
	 *            the newPassWord to set
	 */
	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}

package com.het.ice.model;

import com.het.ice.enums.UserStateEnum;
import com.het.ice.enums.UserTypeEnum;

import java.util.Date;

public class User extends BaseModel {

	/**
	 * uid
	 */
	private static final long serialVersionUID = -7605410496464058030L;

	/** 用户名（会员为手机号） */
	private String userName;

	/** 密码 */
	private String passWord;

	/** 类型（NORMAL,BIZ） */
	private UserTypeEnum type;

	/** 最后登录时间 */
	private Date lastLoginTime;

	/** 会员地址 */
	private String address;

	/** 手机号（BIZ用户的） */
	private String phone;

	/** 姓名 */
	private String realName;

	/** 状态 */
	private UserStateEnum state;

	/** 店名 */
	private String shopName;

	/** 店面照片key */
	private String shopImgKey;

	/** 验证码 */
	private String authCode;

	/** 验证码发送时间 */
	private Date authTime;

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
	 * @return the type
	 */
	public UserTypeEnum getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(UserTypeEnum type) {
		this.type = type;
	}

	/**
	 * @return the lastLoginTime
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime
	 *            the lastLoginTime to set
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName
	 *            the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	public UserStateEnum getState() {
		return state;
	}

	public void setState(UserStateEnum state) {
		this.state = state;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopImgKey() {
		return shopImgKey;
	}

	public void setShopImgKey(String shopImgKey) {
		this.shopImgKey = shopImgKey;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public Date getAuthTime() {
		return authTime;
	}

	public void setAuthTime(Date authTime) {
		this.authTime = authTime;
	}
}

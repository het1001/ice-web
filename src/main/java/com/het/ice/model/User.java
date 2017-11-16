package com.het.ice.model;

import com.het.ice.enums.UserStateEnum;
import com.het.ice.enums.UserTypeEnum;
import net.sf.json.JSONObject;

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

	/** 手机号（BIZ用户的） */
	private String phone;

	/** 姓名 */
	private String realName;

	/** 商铺名称 */
	private String shopName;

	/** 商铺地址 */
	private String shopAddress;

	/** 状态 */
	private UserStateEnum state;

	/** token 校验用户的 */
	private String token;

	/** 属性 */
	private JSONObject property;

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JSONObject getProperty() {
		return property;
	}

	public void setProperty(JSONObject property) {
		this.property = property;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
}

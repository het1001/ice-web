package com.het.ice.model;

public class UserPhoneInfo extends BaseModel {

	private static final long serialVersionUID = 7323446729695403479L;

	/** 用户id */
	private long userId;

	/** 手机号 */
	private String phone;

	/** IMEI 手机入网唯一号 */
	private String imei;

	/** 品牌 */
	private String brand;

	/** 型号 */
	private String model;

	/** 系统版本 */
	private String version;

	/** SDK版本号 */
	private String sdkVersion;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}
}

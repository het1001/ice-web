package com.het.ice.model;

public class UserPhoneInfo extends BaseModel {

	private static final long serialVersionUID = 7323446729695403479L;

	/** 用户id */
	private long userId;

	/** 手机号 */
	private String phone;

	/** 手机产家 */
	private String manufacturer;

	/** 手机型号 */
	private String model;

	/** 设备唯一标识码 */
	private String deviceUniqueId;

	/** 设备ID */
	private String deviceId;

	/** 设备名称 */
	private String deviceName;

	/** 系统名称 */
	private String sysName;

	/** 系统版本 */
	private String sysVersion;

	/** IMEI 手机入网唯一号 */
	private String imei;

	/** app版本 */
	private String appVersion;

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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getSysVersion() {
		return sysVersion;
	}

	public void setSysVersion(String sysVersion) {
		this.sysVersion = sysVersion;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getDeviceUniqueId() {
		return deviceUniqueId;
	}

	public void setDeviceUniqueId(String deviceUniqueId) {
		this.deviceUniqueId = deviceUniqueId;
	}
}

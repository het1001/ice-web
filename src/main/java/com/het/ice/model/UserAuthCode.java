package com.het.ice.model;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/24.
 */
public class UserAuthCode extends BaseModel {

    private static final long serialVersionUID = 4497917864605155992L;

    private String phone;

    /** 验证码 */
    private String code;

    /** 用户收到验证码，是否使用了 */
    private boolean isUsed;

    /** 使用时间 */
    private Date useTime;

    /** 设备唯一标识码 */
    private String deviceUniqueId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public String getDeviceUniqueId() {
        return deviceUniqueId;
    }

    public void setDeviceUniqueId(String deviceUniqueId) {
        this.deviceUniqueId = deviceUniqueId;
    }
}

package com.het.ice.dao.model;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/23.
 */
public class UserAuthCodeDO extends BaseModel {
    private static final long serialVersionUID = -8240312885340014277L;

    private String phone;

    private String code;

    private int used;

    private Date useTime;

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

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
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

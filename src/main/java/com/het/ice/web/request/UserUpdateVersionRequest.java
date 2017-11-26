package com.het.ice.web.request;

import com.het.ice.model.ToString;

/**
 * Created by Administrator on 2017/6/13.
 */
public class UserUpdateVersionRequest extends ToString {

    private static final long serialVersionUID = 3719148813062826800L;
    private String phone;

    private String version;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

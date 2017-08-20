package com.het.ice.dao.query;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/24.
 */
public class AuthCodeQuery implements Serializable {
    private static final long serialVersionUID = 8637521257296062000L;

    private String phone;

    private Date startTime;

    private Date endTime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

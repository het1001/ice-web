package com.het.ice.dao.model;

/**
 * Created by Administrator on 2017/5/29.
 */
public class UserOperateTraceDO extends BaseModel {
    private static final long serialVersionUID = 4016151238676901499L;

    private String phone;

    private String operate;

    private String memo;

    private String oldInfo;

    private String newInfo;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOldInfo() {
        return oldInfo;
    }

    public void setOldInfo(String oldInfo) {
        this.oldInfo = oldInfo;
    }

    public String getNewInfo() {
        return newInfo;
    }

    public void setNewInfo(String newInfo) {
        this.newInfo = newInfo;
    }
}

package com.het.ice.dao.model;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/23.
 */
public class UserInfoDO extends BaseModel {

    private static final long serialVersionUID = 727230220555503247L;

    private String phone;

    private String userName;

    private String shopName;

    private String shopAddress;

    private String shopImgKey;

    private String freezerType;

    private Date arkTime;

    private String freezerModel;

    private long districtId;

    private String standbyPhone;

    private int isAccess;

    private String auditMemo;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public int getIsAccess() {
        return isAccess;
    }

    public void setIsAccess(int isAccess) {
        this.isAccess = isAccess;
    }

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFreezerType() {
        return freezerType;
    }

    public void setFreezerType(String freezerType) {
        this.freezerType = freezerType;
    }

    public Date getArkTime() {
        return arkTime;
    }

    public void setArkTime(Date arkTime) {
        this.arkTime = arkTime;
    }

    public String getFreezerModel() {
        return freezerModel;
    }

    public void setFreezerModel(String freezerModel) {
        this.freezerModel = freezerModel;
    }

    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }

    public String getStandbyPhone() {
        return standbyPhone;
    }

    public void setStandbyPhone(String standbyPhone) {
        this.standbyPhone = standbyPhone;
    }
}

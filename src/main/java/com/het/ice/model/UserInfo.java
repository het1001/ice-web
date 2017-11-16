package com.het.ice.model;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/24.
 */
public class UserInfo extends BaseModel {

    private static final long serialVersionUID = 9084823644073567126L;

    /** 手机号 */
    private String phone;

    /** 姓名 */
    private String userName;

    /** 店铺名称 */
    private String shopName;

    /** 店铺地址 */
    private String shopAddress;

    /** 店铺图片key */
    private String shopImgKey;

    /** 冰柜类型 */
    private String freezerType;

    /** 投柜时间 */
    private Date arkTime;

    /** 冰柜型号 */
    private String freezerModel;

    /** 片区ID */
    private long districtId;

    /** 是否审核通过（0：无审核，1：审核通过，-1：审核不通过） */
    private int isAccess;

    /** 审核意见 */
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
}

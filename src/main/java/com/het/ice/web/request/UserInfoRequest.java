package com.het.ice.web.request;

import com.het.ice.model.ToString;

/**
 * Created by Administrator on 2017/6/4.
 */
public class UserInfoRequest extends ToString {

    private static final long serialVersionUID = -3013825012322536483L;

    private String phone;

    private String userName;

    private String shopName;

    private String shopAddress;

    private String shopImgKey;

    private String freezerType;

    private String arkTime;

    private String freezerModel;

    private String districtId;

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

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopImgKey() {
        return shopImgKey;
    }

    public void setShopImgKey(String shopImgKey) {
        this.shopImgKey = shopImgKey;
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

    public String getArkTime() {
        return arkTime;
    }

    public void setArkTime(String arkTime) {
        this.arkTime = arkTime;
    }

    public String getFreezerModel() {
        return freezerModel;
    }

    public void setFreezerModel(String freezerModel) {
        this.freezerModel = freezerModel;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }
}

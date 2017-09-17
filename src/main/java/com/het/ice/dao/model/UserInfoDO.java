package com.het.ice.dao.model;

/**
 * Created by Administrator on 2017/5/23.
 */
public class UserInfoDO extends BaseModel {

    private static final long serialVersionUID = 727230220555503247L;

    private String phone;

    private String shopName;

    private String shopAddress;

    private String shopImgKey;

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
}

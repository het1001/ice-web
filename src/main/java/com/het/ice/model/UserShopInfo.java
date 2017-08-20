package com.het.ice.model;

/**
 * Created by Administrator on 2017/5/24.
 */
public class UserShopInfo extends BaseModel {

    private static final long serialVersionUID = 9084823644073567126L;

    /** 验证码 */
    private String phone;

    /** 店铺名称 */
    private String shopName;

    /** 店铺地址 */
    private String shopAddress;

    /** 店铺图片key */
    private String shopImgKey;

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
}

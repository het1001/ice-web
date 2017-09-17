package com.het.ice.web.request;

import com.het.ice.model.ToString;

/**
 * Created by Administrator on 2017/6/4.
 */
public class UserInfoRequest extends ToString {

    private static final long serialVersionUID = 2322714090693148909L;

    private String phone;

    private String shopName;

    private String shopAddress;

    private String shopImgKey;

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
}

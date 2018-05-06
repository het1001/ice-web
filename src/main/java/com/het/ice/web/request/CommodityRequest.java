package com.het.ice.web.request;

/**
 * Created by Administrator on 2017/6/13.
 */
public class CommodityRequest {

    private String id;

    private String[] brandId;

    private String[] pricCatId;

    private String[] packCatId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getBrandId() {
        return brandId;
    }

    public void setBrandId(String[] brandId) {
        this.brandId = brandId;
    }


    public String[] getPackCatId() {
        return packCatId;
    }

    public void setPackCatId(String[] packCatId) {
        this.packCatId = packCatId;
    }

    public String[] getPricCatId() {
        return pricCatId;
    }

    public void setPricCatId(String[] pricCatId) {
        this.pricCatId = pricCatId;
    }
}

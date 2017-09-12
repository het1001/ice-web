package com.het.ice.model;

import com.het.ice.enums.SalesmanTypeEnum;

/**
 * Created by Administrator on 2017/9/3.
 */
public class AllotSalesman extends BaseModel {

    private static final long serialVersionUID = 8790587137281693695L;

    /** 名称 */
    private String name;

    private String uniqueKey;

    private String phone;

    private SalesmanTypeEnum salesmanTypeEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SalesmanTypeEnum getSalesmanTypeEnum() {
        return salesmanTypeEnum;
    }

    public void setSalesmanTypeEnum(SalesmanTypeEnum salesmanTypeEnum) {
        this.salesmanTypeEnum = salesmanTypeEnum;
    }
}

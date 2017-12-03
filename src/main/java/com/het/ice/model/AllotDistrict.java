package com.het.ice.model;

import java.util.List;

/**
 * Created by Administrator on 2017/9/3.
 */
public class AllotDistrict extends BaseModel {

    private static final long serialVersionUID = 8790587137281693695L;

    /** 名称 */
    private String name;

    /** 业务员 */
    private List<AllotSalesman> salesmens;

    /** 配送员 */
    private List<AllotSalesman> deliverymens;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AllotSalesman> getSalesmens() {
        return salesmens;
    }

    public void setSalesmens(List<AllotSalesman> salesmens) {
        this.salesmens = salesmens;
    }

    public List<AllotSalesman> getDeliverymens() {
        return deliverymens;
    }

    public void setDeliverymens(List<AllotSalesman> deliverymens) {
        this.deliverymens = deliverymens;
    }
}

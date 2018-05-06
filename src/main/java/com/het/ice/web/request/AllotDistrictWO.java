package com.het.ice.web.request;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/3.
 */
public class AllotDistrictWO implements Serializable {

    private static final long serialVersionUID = 7545940067471149248L;

    private String id;

    private String name;

    private List<String> salesmanIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getSalesmanIds() {
        return salesmanIds;
    }

    public void setSalesmanIds(List<String> salesmanIds) {
        this.salesmanIds = salesmanIds;
    }
}

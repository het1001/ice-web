package com.het.ice.web.request;

import com.het.ice.model.ToString;

import java.util.List;

public class OrderWO extends ToString {

    private static final long serialVersionUID = -5955440115420897532L;

    private String id;

    private String phone;

    private List<String> list;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

package com.het.ice.dao.query;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/6.
 */
public class ShoppingCartQuery implements Serializable {

    private static final long serialVersionUID = 2113149362655783634L;

    private long comId;

    private String phone;

    public long getComId() {
        return comId;
    }

    public void setComId(long comId) {
        this.comId = comId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

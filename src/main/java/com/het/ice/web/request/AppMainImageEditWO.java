package com.het.ice.web.request;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/24.
 */
public class AppMainImageEditWO implements Serializable {

    private static final long serialVersionUID = -226049665260313153L;

    private long id;

    private int active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}

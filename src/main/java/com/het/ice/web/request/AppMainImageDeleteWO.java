package com.het.ice.web.request;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/24.
 */
public class AppMainImageDeleteWO implements Serializable {

    private static final long serialVersionUID = -1264425106392148682L;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

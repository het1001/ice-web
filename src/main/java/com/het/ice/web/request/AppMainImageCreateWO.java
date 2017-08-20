package com.het.ice.web.request;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/24.
 */
public class AppMainImageCreateWO implements Serializable {

    private static final long serialVersionUID = 7545940067471149248L;

    private String name;

    private String imageKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }
}

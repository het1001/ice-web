package com.het.ice.dao.model;

/**
 * Created by Administrator on 2017/5/23.
 */
public class AppMainImgDO extends BaseModel {

    private static final long serialVersionUID = -8240312885340014277L;

    private String name;

    private String imageKey;

    private int active;

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

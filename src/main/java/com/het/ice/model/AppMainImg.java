package com.het.ice.model;

/**
 * Created by Administrator on 2017/5/23.
 */
public class AppMainImg extends BaseModel {

    private static final long serialVersionUID = 8790587137281693695L;

    /** 名称 */
    private String name;

    /** 图片的key */
    private String imageKey;

    /** 是否激活使用 */
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

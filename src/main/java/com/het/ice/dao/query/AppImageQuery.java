package com.het.ice.dao.query;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/29.
 */
public class AppImageQuery implements Serializable {
    private static final long serialVersionUID = -4304548982206333644L;

    private String name;

    private int active;

    private int isMain;

    private int start;

    private int limit;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getIsMain() {
        return isMain;
    }

    public void setIsMain(int isMain) {
        this.isMain = isMain;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

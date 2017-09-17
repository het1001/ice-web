package com.het.ice.dao.query;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/16.
 */
public class PromotionQuery implements Serializable {

    private static final long serialVersionUID = 6821081717687821423L;

    private long comId;

    private String state;

    private Date date;

    private int start;

    private int limit;

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

    public long getComId() {
        return comId;
    }

    public void setComId(long comId) {
        this.comId = comId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

package com.het.ice.web.request;

import java.io.Serializable;
import java.util.Date;

public class PromotionWO implements Serializable {
    private static final long serialVersionUID = -5199944553612769262L;

    private Long Id;

    private long comId;

    private long arithId;

    private String desc;

    private String params;

    private String effectiveDate;

    private String deadline;

    public long getComId() {
        return comId;
    }

    public void setComId(long comId) {
        this.comId = comId;
    }

    public long getArithId() {
        return arithId;
    }

    public void setArithId(long arithId) {
        this.arithId = arithId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
}

package com.het.ice.dao.model;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/16.
 */
public class PromotionDO extends BaseModel {

    private static final long serialVersionUID = 5956073643196152518L;

    private long comId;

    private String comName;

    private long arithId;

    private String description;

    private String params;

    private String state;

    private Date effectiveDate;

    private Date deadline;

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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

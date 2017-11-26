package com.het.ice.model;

import com.het.ice.enums.PromotionStateEnum;

import java.util.Date;
import java.util.Map;

public class Promotion extends BaseModel {
    private static final long serialVersionUID = -3394224613072762274L;

    private long comId;

    private String comName;

    private long arithId;

    private String desc;

    private Map<String, Object> params;

    private PromotionStateEnum state;

    private Date effectiveDate;

    private Date deadline;

    private Commodity commodity;

    public long getComId() {
        return comId;
    }

    public void setComId(long comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
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

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public PromotionStateEnum getState() {
        return state;
    }

    public void setState(PromotionStateEnum state) {
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

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
}

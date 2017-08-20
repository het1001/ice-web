package com.het.ice.web.request;

import com.het.ice.model.ToString;

/**
 * Created by Administrator on 2017/6/13.
 */
public class UserActionRequest extends ToString {

    private static final long serialVersionUID = -4510492480442183819L;

    private String id;

    private String action;

    private String memo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}

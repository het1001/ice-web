package com.het.ice.web.request;

import com.het.ice.model.ToString;

/**
 * Created by Administrator on 2017/5/23.
 */
public class UserSetPassWordRequest extends ToString {

    private static final long serialVersionUID = -9145416864264208506L;

    private String phone;

    private String passWord;

    private String type;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
